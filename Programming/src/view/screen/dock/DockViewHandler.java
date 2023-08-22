package view.screen.dock;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.exception.EcoBikeRentalException;
import controller.ReturnBikeController;
import controller.view.ViewDockController;
//import controller.RentBikeController;
//import controller.ReturnBikeController;
//import controller.ViewBikeController;
import entity.bike.Bike;
import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import view.returnbike.ReturnBikeHandler;
import view.screen.BaseScreenHandler;

public class DockViewHandler extends BaseScreenHandler implements Initializable{
	
	@FXML
	private Button dockBackBtn, returnBtn;
	
	@FXML
	private Label dockAddressLabel, dockAreaLabel, emptyPointsLabel, dockNameLabel, bikeAvailableLabel;
	
	@FXML
	private ImageView dockImage;
	
	@FXML
    private VBox bikeListVbox;
	
	private Dock dock;

	public DockViewHandler(Stage stage, String screenPath, Dock dock) throws IOException, SQLException {
		super(stage, screenPath);
		this.dock = dock;
		
		this.dockAddressLabel.setText(dock.getAddress());
		this.dockAreaLabel.setText("" + dock.getArea() + " m2");
		this.dockNameLabel.setText(dock.getName());
		this.emptyPointsLabel.setText("" + dock.getNoOfEmptyPoints());
		this.bikeAvailableLabel.setText("" + dock.getNoOfBikes());
	    Image imageLink = new Image(dock.getImageUrl());
        dockImage.setImage(imageLink);
        dockImage.setPreserveRatio(false);
        
		showDockInfo();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		dockBackBtn.setOnMouseClicked(e -> {
			this.getPreviousScreen().show();
		});
		
		returnBtn.setOnMouseClicked(e -> {
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
		
	}

	private void showDockInfo() throws SQLException {
		//get list bike in dock
		 ArrayList<Bike> bikeList = null;
		try {
			bikeList = ViewDockController.viewDockInfo(dock.getDockId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	        bikeListVbox.getChildren().clear();

	        // display list bike
	        try {
	            displayBikes(bikeList);
	        } catch (IOException exception) {
	            throw new EcoBikeRentalException(exception.getMessage());
	        }
	}
	
    private void displayBikes(ArrayList<Bike> bikeList) throws IOException, SQLException {
        for (Bike bike : bikeList) {            
            BikeInDockHandler bikeInDockHandler = new BikeInDockHandler(Configs.BIKE_IN_DOCK_PATH, this);
            bikeInDockHandler.setBike(bike);
            bikeInDockHandler.setDock(this.dock);
            bikeInDockHandler.setBikeInfo();
            bikeListVbox.getChildren().add(bikeInDockHandler.getContent());
        }
    }
}