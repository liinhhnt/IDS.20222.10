package view.screen.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import common.exception.InvalidSearchKeyException;
import common.exception.NoResultException;
import controller.HomeScreenController;
import controller.search.SearchDockByAddressController;
import controller.search.SearchDockByNameController;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import view.handler.rentbike.RentBikeInfoHandler;
import view.handler.view.Alert;
import view.handler.view.MouseEvent;
import view.handler.view.TextInputDialog;
import view.screen.BaseScreenHandler;
import view.screen.dock.DockHandler;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private Button searchBtn, returnBikeBtn;

    @FXML
    private VBox dockListVBox;

    @FXML
    private RadioButton searchByNameBtn;

    @FXML
    private RadioButton searchByAddressBtn;

    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        
    	searchByNameBtn.setOnAction(e -> {
            searchByNameBtn.setSelected(true);
            HomeScreenController homeCtrl = (HomeScreenController) this.getBaseController();
            homeCtrl.setSearchController(new SearchDockByNameController());
            System.out.println("Search by Name");
        });

        searchByAddressBtn.setOnAction(e -> {
            searchByAddressBtn.setSelected(true); 
            HomeScreenController homeCtrl = (HomeScreenController) this.getBaseController();
            homeCtrl.setSearchController(new SearchDockByAddressController());
            System.out.println("Search by Address");
        });

        searchBtn.setOnMouseClicked(e -> {
            try {
                HomeScreenController homeCtrl = (HomeScreenController) this.getBaseController();
                ArrayList<Dock> result = homeCtrl.search(searchTextField.getText());
                showDockList(result);
            } catch (InvalidSearchKeyException ex) {
                System.out.println(ex.getMessage());
            } catch (NoResultException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Something happened unexpectedly");
                ex.printStackTrace();
            }
        });
        
        returnBikeBtn.setOnMouseClicked(e -> {
			ReturnBikeController returnctrl = new ReturnBikeController();
			try {
				ReturnBikeHandler returnScreen = new ReturnBikeHandler(this.stage, Configs.RETURN_SCREEN_PATH, this.dock);
				returnScreen.setPreviousScreen(this);
				returnScreen.setBaseController(returnctrl);
				returnScreen.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			}
		);
        
		showAllDocks();

        
    }

	
    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        this.setHomeScreenHandler(this);
    }

    @Override
    public void show() {
        super.show();
    }

    private void showAllDocks() {
        showDockList(HomeScreenController.getAllDocks());
    }

    private void showDockList(ArrayList<Dock> docks) {
        dockListVBox.getChildren().clear();
        try {
            for (Dock dock : docks) {
                DockHandler dockHandler = new DockHandler(Configs.DOCK_PATH, this);
                dockHandler.setDock(dock);
                dockListVBox.getChildren().add(dockHandler.getContent());
                dockListVBox.setSpacing(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void enterBarcodeHandler(MouseEvent event) throws SQLException {
        try {
            TextInputDialog td = new TextInputDialog();
            td.setTitle("Enter bar code");
            td.setHeaderText("Nhập barcode để thuê xe:");
            td.setContentText("Barcode");

            Optional<String> result = td.showAndWait();
            if (result.isPresent()) {
                // System.out.println(result.get());
                RentBikeInfoHandler rentBikeInfoHandler = new RentBikeInfoHandler(Configs.RENT_BIKE_INFO_SCREEN_PATH,
                        this.stage,
                        rentBikeController.getBikeByBikeId(rentBikeController.convertBarcodeToBikeId(result.get())),
                        result.get());
                rentBikeInfoHandler.setPreviousScreen(this);
                rentBikeInfoHandler.setHomeScreenHandler(homeScreenHandler);
                rentBikeInfoHandler.setScreenTitle("Rent bike info");
                rentBikeInfoHandler.show();
            }
        } catch (Exception e) {
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Barcode không hợp lệ");
            alert.showAndWait();
        }
    }
}
