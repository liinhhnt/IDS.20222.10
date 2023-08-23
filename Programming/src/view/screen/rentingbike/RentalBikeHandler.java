package view.screen.rentingbike;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import common.exception.EcoBikeRentalException;
import controller.rent_bike.RentBikeController;
import data_access_layer.bike.Bike_DAL;
import entity.bike.Bike;
import entity.bike.BikeType;
import entity.rent_bike.RentInfo;
import entity.bike.StandardEBike;
import entity.dock.Dock;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
//import entity.transaction.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Utils;
import utils.Configs;
import view.screen.BaseScreenHandler;
import view.screen.FXMLScreenHandler;

public class RentalBikeHandler extends BaseScreenHandler {
	@FXML
	private Label barcode, typeBike, battery, deposit, licensePlate, pedals, remainingTime, minutes, seconds, hours,
			saddles, rearSeats, batteryLabel, remainingTimeLabel;
	@FXML
	private ImageView image;
	@FXML
	private Button returnBike, backBtn;

	private Bike bike;
	private long h, m, s;
	private Timeline timeline;

	public RentalBikeHandler(Stage stage, String screenPath, Bike bike, BikeType bikeType, long hour, long minute,
			long second) throws IOException {
		// TODO Auto-generated constructor stub
		super(stage, screenPath);
		this.bike = bike;
		this.h = hour;
		this.m = minute;
		this.s = second;
		barcode.setText(bike.getBarCode());
		typeBike.setText(bikeType.getName());
		licensePlate.setText(bike.getLicensePlate());
		saddles.setText("" + bikeType.getNoSaddles());
		pedals.setText("" + bikeType.getNoPedals());
		rearSeats.setText("" + bikeType.getNoSaddles());
		hours.setText(String.format("%02d", hour));
		minutes.setText(String.format("%02d", minute));
		seconds.setText(String.format("%02d", second));
		
		Image imageLink = new Image(bike.getImgUrl());
        image.setImage(imageLink);
		
		battery.setVisible(false);
		batteryLabel.setVisible(false);

		remainingTime.setVisible(false);
		remainingTimeLabel.setVisible(false);
		
		switch (bike.getType()) {
		case StandardEBike.BIKE_TYPE_VALUE:
			setEBikeAttrData();
			break;
		default:
			break;
		}
		startTimer();
		backBtn.setOnMouseClicked(e -> {
			this.getPreviousScreen().show();
		});
	}
	private void setEBikeAttrData() {
        StandardEBike eBike;
        batteryLabel.setVisible(true);
        battery.setVisible(true);
        remainingTime.setVisible(true);
        remainingTimeLabel.setVisible(true);

        eBike = (StandardEBike) bike;
        battery.setText(""+eBike.getBatteryPercent() + "%");
        remainingTime.setText("" + eBike.getRemainingTime());
    }
	private void startTimer() {

		timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
			s++;
			if (s == 60) {
				s = 0;
				m++;
				if (m == 60) {
					m = 0;
					h++;
				}
			}
			hours.setText(String.format("%02d", h));
			minutes.setText(String.format("%02d", m));
			seconds.setText(String.format("%02d", s));
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}
