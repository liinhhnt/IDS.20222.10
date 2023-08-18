package view.screen.payment;
import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import view.screen.BaseScreenHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
public class InputCardHandler extends BaseScreenHandler{
	@FXML
	private Button backBtn, confirmBtn;
	
	@FXML
	private TextField cardHolder, cardSecurity, cardNumber, cardExpDate, privateToken;
	
	@FXML
	private Label invalidName, invalidFormat, invalidCardSecurity, invalidCardNumber, invalidToken;
	
	public InputCardHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		initializable();
	}
	private String holderName, privToken, expDate, cardSer, cardCode;
	
	private void initializable() {
		// TODO Auto-generated method stub
		 invalidCardNumber.setVisible(false);
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
		cardCode = cardNumber.getText();
		boolean  isValid;
		isValid = validateFields();
		if (isValid) {
			
		}
	}

	private boolean validateFields() {
		// TODO Auto-generated method stub
		boolean isValid = true;
		if(holderName.isEmpty()) {
			invalidName.setVisible(true);
			isValid = false;
		}
		else
			invalidName.setVisible(false);
		if(privToken.isEmpty()) {
			invalidToken.setVisible(true);
			isValid = false;
		}
		else
			invalidToken.setVisible(false);
		if(cardCode.isEmpty() || !cardCode.matches("\\d{16}")) {
			invalidCardNumber.setVisible(true);
			isValid = false;
		}
		else
			invalidCardNumber.setVisible(false);
		if(cardCode.isEmpty() || !cardCode.matches("\\d{16}")) {
			invalidCardNumber.setVisible(true);
			isValid = false;
		}
		else
			invalidCardNumber.setVisible(false);
		if (!expDate.matches("\\d{2}/\\d{2}") || expDate.isEmpty()) {
		    invalidFormat.setVisible(true);
		    isValid = false;
		}
		else {
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
		    }
		    else {
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
