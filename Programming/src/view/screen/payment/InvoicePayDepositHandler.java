package view.screen.payment;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javafx.stage.Stage;
import utils.Configs;
import view.screen.BaseScreenHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import entity.bike.Bike;
import entity.bike.BikeType;
import entity.invoice.Invoice;
import data_access_layer.card.Card_DAL;
import view.screen.home.HomeScreenHandler;

public class InvoicePayDepositHandler extends BaseScreenHandler{
	@FXML
	private Label  type, licensePlate, deposit, barcode, transId, cardHolder, cardCode, dateTime;
	@FXML
	private Button returnBtn;
	
	private Bike bike;
	private BikeType bikeType;
	private Invoice invoice;
	public InvoicePayDepositHandler(Stage stage, String screenPath, Invoice invoice, BikeType bikeType, Bike bike) throws IOException {
		super(stage, screenPath);
		this.bikeType = bikeType;
		this.bike = bike;
		this.invoice = invoice;
		initializable();
		returnBtn.setOnMouseClicked(e -> {
			try {
				HomeScreenHandler newHome = new HomeScreenHandler(this.homeScreenHandler.getStage(), Configs.HOME_SCREEN_PATH);
				newHome.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	private void initializable() {
		type.setText(bikeType.getName());
		licensePlate.setText(bike.getLicensePlate());
		barcode.setText(bike.getBarCode());
		transId.setText(Integer.toString(invoice.getInvoiceId()));
		deposit.setText(Integer.toString(invoice.getDeposit()));
		cardHolder.setText(Card_DAL.getCardHolder(invoice.getcardRent()));
		cardCode.setText(invoice.getcardRent());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		dateTime.setText(invoice.getStartTime().format(formatter));
	}
}
