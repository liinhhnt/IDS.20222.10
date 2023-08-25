package view.returnbike;
/*
 * This is dock list for return bike use case
 * return bike dock list boundary
 * author: linh.pk
 */

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controller.return_bike.ReturnBikeController;
import data_access_layer.card.Card_DAL;
import data_access_layer.invoice.Invoice_DAL;
import entity.bike.Bike;
import entity.bike.BikeType;
import entity.dock.Dock;
import entity.invoice.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import view.screen.BaseScreenHandler;
import view.screen.home.HomeScreenHandler;
import view.screen.payment.InputCardReturnHandler;

public class ReturnBikeHandler extends BaseScreenHandler {
	@FXML
	private Label invoiceId, startTime, totalTime, bikeId, bikeType, deposit, rentFeeLabel, refundLabel, amoutLabel;
	@FXML
	private Button backBtn, returnBtn;
	@FXML
	private ImageView image;

	private Dock dock;
	private Bike bike;
	private BikeType bikeT;
	private Invoice invoice;
	private int depo, rentalFee, refund;
	private ReturnBikeController rcl;
	private LocalDateTime currentTime;

	public ReturnBikeHandler(Stage stage, String screenPath, Dock dock, Bike bike, BikeType bikeT) throws IOException {
		super(stage, screenPath);
		this.dock = dock;
		this.bike = bike;
		this.rcl = new ReturnBikeController();
		try {
			this.invoice = Invoice_DAL.getRentInvoice(bike);
			this.invoiceId.setText(String.format("%d", this.invoice.getInvoiceId()));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			this.startTime.setText(invoice.getStartTime().format(formatter));
			LocalDateTime start = invoice.getStartTime();
			System.out.println("start" + start);
			this.currentTime = LocalDateTime.now();
			System.out.println("current" + currentTime);
			java.time.Duration duration = java.time.Duration.between(start.toLocalTime(),
					this.currentTime.toLocalTime());
			long hours = duration.toHours();
			long minutes = duration.toMinutesPart();
			long seconds = duration.toSecondsPart();
			String timeText = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			System.out.println("Time" + timeText);
			this.totalTime.setText(timeText);
			this.bikeId.setText(String.format("%d", bike.getBikeId()));
			this.bikeType.setText(bikeT.getName());
			Image imageLink = new Image(bike.getImgUrl());
			this.image.setImage(imageLink);
			this.depo = invoice.getDeposit();
			this.deposit.setText(String.format("%d", this.depo));
			this.rentalFee = rcl.calculateFee(this.invoice);
			this.rentFeeLabel.setText(String.format("%d", rentalFee));
			this.refund = this.depo - this.rentalFee;
			this.backBtn.setOnMouseClicked(e -> {
				this.getPreviousScreen().show();
			});
			if (this.refund > 0) {
				this.refundLabel.setText(String.format("%d", this.refund));
				this.amoutLabel.setText("0");
				this.returnBtn.setOnMouseClicked(e -> {
					payRefund();
				});
			} else {
				this.refundLabel.setText("0");
				this.amoutLabel.setText(String.format("%d", 0 - this.refund));
				this.returnBtn.setOnMouseClicked(e -> {
					payAmount();
				});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void payAmount() {
		// TODO Auto-generated method stub
		try {
			InputCardReturnHandler ip = new InputCardReturnHandler(this.getStage(), Configs.INPUT_CARD_SCREEN_PATH,
					this.bike, this.refund, this.rentalFee, this.currentTime, this.dock, this.invoice);
			ip.setPreviousScreen(this);
			ip.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void payRefund() {
		// TODO Auto-generated method stub
		try {
			Card_DAL.payment(this.invoice.getCardNumber(), this.refund, false);
			this.rcl.updateTotalTime(this.invoice, this.currentTime);
			this.rcl.updateTotalMoney(rentalFee, invoice);
			this.rcl.updateInvoice(this.invoice);
			this.rcl.updateAfterReturnBike(this.bike.getBikeId(), this.dock.getDockId());
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("REFUND AND RETURN SUCCESSFULLY!");
			alert.setContentText("You have successfully return a bike!");
			alert.showAndWait();
			HomeScreenHandler newHome = new HomeScreenHandler(this.homeScreenHandler.getStage(),
					Configs.HOME_SCREEN_PATH);
			newHome.show();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}