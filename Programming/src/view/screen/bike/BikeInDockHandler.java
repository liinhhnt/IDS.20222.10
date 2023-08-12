package view.screen.bike;

import java.io.IOException;
import java.sql.SQLException;

import controller.view.ViewDockController;
import utils.Utils;
import utils.Configs;
import entity.bike.Bike;
import entity.bike.BikeType;
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
    private BikeType bikeType;

    public BikeInDockHandler(String screenPath, DockViewHandler dockViewHandler)
            throws IOException {
        super(screenPath);
        this.dockViewHandler = dockViewHandler;
        this.content = (AnchorPane)getLoader().load();
    }

    public AnchorPane getContent() {
        return this.content;
      }
    
    public void setBike(Bike bike) throws SQLException {
        this.bike = bike;
        this.bikeType = ViewDockController.getBikeType(bike.getType());
    }
    
    public void setBikeInfo() throws SQLException {
        barCodeLabel.setText("Barcode: "+bike.getBarCode());
    	
    	bikeTypeString.setText(bikeType.getName());

        lisenceLabel.setText("Lisence plate: "+ bike.getLicensePlate());

        depositeAmountLabel.setText("Deposit: " + Utils.calculateDeposit(bike.getType()));

        // set image
        Image imageLink = new Image(bike.getImgUrl());
        bikeImage.setImage(imageLink);
        bikeImage.setPreserveRatio(false);

        viewBikeInfoButton.setOnMouseClicked(event -> {
        	BikeViewHandler bikeViewHandler;
			try {
				bikeViewHandler = new BikeViewHandler(this.dockViewHandler.getStage(), Configs.BIKE_VIEW_PATH,
				         bike, bikeType);
				bikeViewHandler.setPreviousScreen(this.dockViewHandler);
	        	bikeViewHandler.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        });
    }
}