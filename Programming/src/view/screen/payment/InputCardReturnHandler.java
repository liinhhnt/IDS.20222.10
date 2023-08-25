package view.screen.payment;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import controller.return_bike.ReturnBikeController;
import javafx.fxml.FXML;
import view.screen.BaseScreenHandler;
import view.screen.home.HomeScreenHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import entity.bike.Bike;
import entity.dock.Dock;
import entity.invoice.Invoice;
import data_access_layer.card.Card_DAL;

public class InputCardReturnHandler extends BaseScreenHandler {
	@FXML
	private Button backBtn, confirmBtn;

	@FXML
	private TextField cardHolder, cardSecurity, cardNumber, cardExpDate, privateToken;

	@FXML
	private Label invalidName, invalidFormat, invalidCardSecurity, invalidcardNumber, invalidToken;
	private Bike bike;
	private int fee;
	private int rentalFee;
	private Dock dock;
	private Invoice invoice;
	private LocalDateTime currentTime;

	public InputCardReturnHandler(Stage stage, String screenPath, Bike bike, int fee, int rentalFee,
			LocalDateTime currentTime, Dock dock, Invoice invoice) throws IOException {
		super(stage, screenPath);
		this.bike = bike;
		this.fee = fee;
		this.rentalFee = rentalFee;
		this.dock = dock;
		this.currentTime = currentTime;
		this.invoice = invoice;
		initializable();
	}

	private String holderName, privToken, expDate, cardSer, cardCode;

	private void initializable() {
		// TODO Auto-generated method stub
		invalidcardNumber.setVisible(false);
		invalidCardSecurity.setVisible(false);
		invalidFormat.setVisible(false);
		invalidName.setVisible(false);
		invalidToken.setVisible(false);
		backBtn.setOnMouseClicked(e -> {
			this.getPreviousScreen().show();
		});
		confirmBtn.setOnMouseClicked(e -> {
			this.confirmReturn();
		});
	}

	private void confirmReturn() {
		// TODO Auto-generated method stub
		holderName = cardHolder.getText();
		privToken = privateToken.getText();
		expDate = cardExpDate.getText();
		cardSer = cardSecurity.getText();
		cardCode = cardNumber.getText();
		boolean isValid;
		boolean cardCheck;
		isValid = validateFields();
//		cardCheck = Card_DAL.checkCard(cardCode, deposit, holderName, expDate, cardSer);
		cardCheck = Card_DAL.checkCardReturn(cardCode, fee, holderName, expDate, cardSer);
		if (isValid == true && cardCheck == true) {
			ReturnBikeController ud = new ReturnBikeController();
			try {
				Card_DAL.payment(cardCode, this.fee, false);
				ud.updateTotalTime(this.invoice, this.currentTime);
				ud.updateTotalMoney(rentalFee, invoice);
				ud.updateInvoice(this.invoice);
				ud.updateAfterReturnBike(this.bike.getBikeId(), this.dock.getDockId());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("SUCCESSFULLY!");
				alert.setContentText("You have successfully return a bike!");
				alert.showAndWait();
				HomeScreenHandler newHome = new HomeScreenHandler(this.homeScreenHandler.getStage(), Configs.HOME_SCREEN_PATH);
				newHome.show();
			} catch (SQLException | IOException e) {
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
			invalidcardNumber.setVisible(true);
			isValid = false;
		} else
			invalidcardNumber.setVisible(false);
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
