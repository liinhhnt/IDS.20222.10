package view.screen.bike;

import java.io.IOException;
import java.sql.SQLException;

import controller.view.ViewDockController;
import utils.Utils;
import entity.bike.Bike;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.screen.FXMLScreenHandler;
import view.screen.dock.DockViewHandler;


public class BikeInDockHandler extends FXMLScreenHandler {

    @FXML
    private Label barCodeLabel, bikeTypeString, lisenceLabel, depositeAmountLabel;

    @FXML
    private HBox hboxBike;

    @FXML
    private VBox imageLogoVbox;

    @FXML
    private ImageView bikeImage;

    @FXML
    private Button viewBikeInfoButton;

    @FXML
    private VBox spinnerFX;
    
    private AnchorPane content;
    private DockViewHandler dockViewHandler;
    private Bike bike;

    public BikeInDockHandler(String screenPath, DockViewHandler dockViewHandler)
            throws IOException {
        super(screenPath);
        this.dockViewHandler = dockViewHandler;
        this.content = (AnchorPane)getLoader().load();
    }

    public AnchorPane getContent() {
        return this.content;
      }
    
    public void setBike(Bike bike) {
        this.bike = bike;
    }
    
    public void setBikeInfo() throws SQLException {
        barCodeLabel.setText("Barcode: "+bike.getBarCode());
    	
    	bikeTypeString.setText(ViewDockController.getByTypeName(this.bike.getType()));

        lisenceLabel.setText("Lisence plate: "+ bike.getLicensePlate());

        depositeAmountLabel.setText("Deposit: " + Utils.calculateDeposit(bike.getType()));

        // set image
        Image imageLink = new Image(bike.getImgUrl());
        bikeImage.setImage(imageLink);
        bikeImage.setPreserveRatio(false);

        viewBikeInfoButton.setOnMouseClicked(event -> {
//        	ViewDockBikeInfoHandler viewDockBikeInfoHandler = new ViewDockBikeInfoHandler(Configs.BIKE_DETAIL_SCREEN_PATH,
//                    this.stage, bike);
//            viewDockBikeInfoHandler.setPreviousScreen(this);
//            viewDockBikeInfoHandler.setHomeScreenHandler(homeScreenHandler);
//            viewDockBikeInfoHandler.setScreenTitle("Bike Info");
//            viewDockBikeInfoHandler.show();
        });
    }
}