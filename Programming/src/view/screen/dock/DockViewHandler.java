package view.screen.dock;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;
import utils.Configs;
import view.screen.BaseScreenHandler;
//import view.screen.rent.RentHandler;
//import view.screen.returnBike.ReturnBikeHandler;
//import view.screen.viewbike.ViewBikeHandler;

public class DockViewHandler extends BaseScreenHandler implements Initializable{
	
	@FXML
	private Button dockBackBtn;
	
//	@FXML
//	private Button viewBikeBtn;
	
	@FXML
	private Label dockAddressLabel, dockAreaLabel, emptyPointsLabel, dockNameLabel, bikeAvailableLabel;
	
	@FXML
	private ImageView dockImage;
	
	private Dock dock;
	
	public DockViewHandler(Stage stage, String screenPath, Dock dock) throws IOException {
		super(stage, screenPath);
		this.dock = dock;
		this.dockAddressLabel.setText(dock.getAddress());
		this.dockAreaLabel.setText("" + dock.getArea());
		this.dockNameLabel.setText(dock.getName());
		this.emptyPointsLabel.setText("" + dock.getNoOfEmptyPoints());
		this.bikeAvailableLabel.setText("" + dock.getNoOfBikes());
	    Image imageLink = new Image(dock.getImageUrl());
        dockImage.setImage(imageLink);
        dockImage.setPreserveRatio(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dockBackBtn.setOnMouseClicked(e -> {
			this.getPreviousScreen().show();
		});		
	}
}
