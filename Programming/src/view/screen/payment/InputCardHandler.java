package view.screen.payment;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import controller.rent_bike.RentBikeController;
import javafx.fxml.FXML;
import view.screen.BaseScreenHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import data_access_layer.invoice.Invoice_DAL;
import entity.bike.Bike;
import entity.invoice.Invoice;
import data_access_layer.card.Card_DAL;
import data_access_layer.bike.BikeType_DAL;

public class InputCardHandler extends BaseScreenHandler {
	@FXML
	private Button backBtn, confirmBtn;

	@FXML
	private TextField cardHolder, cardSecurity, cardRent, cardExpDate, privateToken;

	@FXML
	private Label invalidName, invalidFormat, invalidCardSecurity, invalidcardRent, invalidToken;
	private Bike bike;
	private int deposit;

	public InputCardHandler(Stage stage, String screenPath, Bike bike, int deposit) throws IOException {
		super(stage, screenPath);
		this.bike = bike;
		this.deposit = deposit;
		initializable();
	}

	private String holderName, privToken, expDate, cardSer, cardCode;

	private void initializable() {
		// TODO Auto-generated method stub
		invalidcardRent.setVisible(false);
		invalidCardSecurity.setVisible(false);
		invalidFormat.setVisible(false);
		invalidName.setVisible(false);
		invalidToken.setVisible(false);
		backBtn.setOnMouseClicked(e -> {
			this.getPreviousScreen().show();
		});
		confirmBtn.setOnMouseClicked(e -> {
			this.confirmRent();
		});
	}

	private void confirmRent() {
		// TODO Auto-generated method stub
		holderName = cardHolder.getText();
		privToken = privateToken.getText();
		expDate = cardExpDate.getText();
		cardSer = cardSecurity.getText();
		cardCode = cardRent.getText();
		boolean isValid;
		boolean cardCheck;
		isValid = validateFields();
		cardCheck = Card_DAL.checkCard(cardCode, deposit, holderName, expDate, cardSer);
		if (isValid == true && cardCheck == true) {
			RentBikeController ud = new RentBikeController();
			Invoice invoice = new Invoice(bike, deposit, cardCode);
			Invoice_DAL i = new Invoice_DAL();
			try {
				ud.updateAfterRentBike(bike.getBikeId(), bike.getType(), bike.getDockId());
				Card_DAL.payDeposit(cardCode, deposit);
				i.saveNewInvoice(invoice);
				InvoicePayDepositHandler ip = new InvoicePayDepositHandler(this.homeScreenHandler.getStage(), Configs.INVOICE_PAYDEPOSIT,
						invoice, BikeType_DAL.getById(bike.getBikeId()), bike);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
    			alert.setTitle("SUCCESSFULLY!");
    			alert.setContentText("You have successfully rented a bike!");
    			alert.showAndWait();
				ip.show();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private boolean validateFields() {
		// TODO Auto-generated method stub
		boolean isValid = true;
		if (holderName.isEmpty()) {
			invalidName.setVisible(true);
			isValid = false;
		} else
			invalidName.setVisible(false);
		if (privToken.isEmpty()) {
			invalidToken.setVisible(true);
			isValid = false;
		} else
			invalidToken.setVisible(false);
		if (cardCode.isEmpty()) {
			invalidcardRent.setVisible(true);
			isValid = false;
		} else
			invalidcardRent.setVisible(false);
		if (!expDate.matches("\\d{2}/\\d{2}") || expDate.isEmpty()) {
			invalidFormat.setVisible(true);
			isValid = false;
		} else {
			String[] dateParts = expDate.split("/");
			int month = Integer.parseInt(dateParts[0]);
			int year = Integer.parseInt(dateParts[1]) + 2000;

			// Lấy ngày hiện tại
			LocalDate currentDate = LocalDate.now();
			int currentYear = currentDate.getYear();
			int currentMonth = currentDate.getMonthValue() + 1;

			// Kiểm tra năm hợp lệ
			if (year < currentYear || month < 1 || month > 12 || (year == currentYear && month < currentMonth)) {
				invalidFormat.setVisible(true);
				isValid = false;
			} else {
				invalidFormat.setVisible(false);
			}
		}
		if (cardSer.isEmpty() || !cardSer.matches("\\d{3}")) {
			invalidCardSecurity.setVisible(true);
			isValid = false;
		} else {
			invalidCardSecurity.setVisible(false);
		}
		return isValid;
	}

}
