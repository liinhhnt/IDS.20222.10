package view.screen.rentingbike;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import controller.view.ViewDockController;
import data_access_layer.invoice.Invoice_DAL;
import entity.bike.Bike;
import entity.bike.BikeType;
import entity.dock.Dock;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import utils.Configs;
import utils.Utils;
import view.screen.FXMLScreenHandler;
import view.screen.bike.BikeViewHandler;
import view.screen.dock.DockViewHandler;

public class RentalBikeInList extends FXMLScreenHandler {
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
	private RentalListHandler rentalList;
	private Bike bike;
	private BikeType bikeType;

	private Timeline timeline;

	private long seconds;

	private long minutes;

	private long hours;
	private Dock dock = null;
	public void setDock(Dock dock) {
		this.dock = dock;
	}

	public RentalBikeInList(String screenPath, RentalListHandler rentalList) throws IOException {
		super(screenPath);
		this.rentalList = rentalList;
		this.content = (AnchorPane) getLoader().load();
	}

	public AnchorPane getContent() {
		return this.content;
	}

	public void setBike(Bike bike) throws SQLException {
		this.bike = bike;
		this.bikeType = ViewDockController.getBikeType(bike.getType());
	}

	public void setBikeInfo() throws SQLException {
		barCodeLabel.setText("Barcode: " + bike.getBarCode());

		bikeTypeString.setText(bikeType.getName());

		lisenceLabel.setText("Lisence plate: " + bike.getLicensePlate());

		depositeAmountLabel.setText("Time: 00:00:00");

		// set image
		Image imageLink = new Image(bike.getImgUrl());
		bikeImage.setImage(imageLink);
		bikeImage.setPreserveRatio(false);
		LocalDateTime start = Invoice_DAL.getStartTime(bike.getBikeId());
		LocalDateTime currentTime = LocalDateTime.now();
		java.time.Duration duration = java.time.Duration.between(start.toLocalTime(), currentTime.toLocalTime());
		hours = duration.toHours();
		minutes = duration.toMinutesPart();
		seconds = duration.toSecondsPart();
		startTimer(depositeAmountLabel);
        viewBikeInfoButton.setOnMouseClicked(event -> {
        	RentalBikeHandler bikeViewHandler;
			try {
				bikeViewHandler = new RentalBikeHandler(this.rentalList.getStage(), Configs.RENTING_SCREEN_PATH,
				         bike, bikeType, hours, minutes, seconds, dock);
				bikeViewHandler.setPreviousScreen(this.rentalList);
	        	bikeViewHandler.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        });
	}

	private void startTimer(Label timerLabel) {

		timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
			seconds++;
			if (seconds == 60) {
				seconds = 0;
				minutes++;
				if (minutes == 60) {
					minutes = 0;
					hours++;
				}
			}
			String timeText = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			timerLabel.setText("Time: " + timeText);
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}
