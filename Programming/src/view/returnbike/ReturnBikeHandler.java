package view.returnbike;
/*
 * This is dock list for return bike use case
 * return bike dock list boundary
 * author: linh.pk
 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.ResourceBundle;
import controller.ReturnBikeController;
import entity.dock.Dock;
import entity.invoice.Invoice;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import view.screen.BaseScreenHandler;

public class ReturnBikeHandler extends BaseScreenHandler implements Initializable{
	@FXML
	private Button backBtn, returnBtn, okeBtn, goHomeBtn ;
	@FXML
	private Label returnInfo, notReturnBike, aLabel, rentIdLabel, startRentLabel, returnTimeLabel, bikeIdLabel, rentTypeLabel, depositLabel, rentFeeLabel, refundLabel, amoutLabel
	, rentLabel, startLabel, timeLabel, bikeLabel, typeLabel, dLabel, reLabel, refLabel, ownLabel, caLabel, exLabel, seLabel;
	private Dock dock;
	@FXML
	private TextField ownerRField, cardNumRField, expiryRField, cvvRField;
	@FXML
	private Line lineR;
	
	@FXML
	private Label ownerRLabel, numberRLabel, dateRLabel, cvvRLabel, rentRInfo;
	public ReturnBikeHandler(Stage stage, String screenPath, Dock dock) throws IOException {
		super(stage, screenPath);
		this.dock = dock;
		this.okeBtn.setOpacity(0);
		this.okeBtn.setDisable(true);
		this.goHomeBtn.setOpacity(0);
		this.goHomeBtn.setDisable(true);
		this.returnInfo.setOpacity(0);
		this.returnInfo.setDisable(true);
//		this.notReturnBike.setOpacity(0);
		this.notReturnBike.setDisable(true);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ReturnBikeController returnBike = new ReturnBikeController();
		returnBike.setCurrentDock(this.dock);
		Map<String, String> invoiceInfo = returnBike.getInvoiceInfo();
		if(invoiceInfo.get("STATE").equals("RETING")) {
			this.rentIdLabel.setText(invoiceInfo.get("INVOICE_ID"));
			this.startRentLabel.setText(invoiceInfo.get("START"));
			this.returnTimeLabel.setText(invoiceInfo.get("REN_TIME"));
			this.bikeIdLabel.setText(invoiceInfo.get("BIKE_ID"));
			this.rentTypeLabel.setText(invoiceInfo.get("RENT_TYPE"));
			this.depositLabel.setText(invoiceInfo.get("DEPOSIT_MONEY"));
			this.rentFeeLabel.setText(invoiceInfo.get("RENT_FEE"));
			this.refundLabel.setText(invoiceInfo.get("REFUND"));
			this.amoutLabel.setText(invoiceInfo.get("AMOUNT"));
//			this.returnBtn.setOnMouseClicked(e -> {
//				if(!cardNumRField.getText().equals("")  && !ownerRField.getText().equals("") && !cvvRField.getText().equals("") && !expiryRField.getText().equals("")) {
//				disable();
//				returnBike.setCurrentDock(this.dock);
//				Map<String, String> responseToPay = new Hashtable<String, String>();
//				try {
//				  responseToPay = returnBike.returnBike(cardNumRField.getText(), ownerRField.getText(), expiryRField.getText(), cvvRField.getText());
//				  if(responseToPay.get("RESULT").equals("RENT FAILED!")) {
//	    			
//	    			this.returnInfo.setOpacity(1);
//	    			this.returnInfo.setDisable(false);    			
//	    			this.okeBtn.setOpacity(1);
//	    			this.okeBtn.setDisable(false);
//	    			this.returnInfo.setText(responseToPay.get("MESSAGE"));
//	    		}else {
//	    			this.returnInfo.setOpacity(1);
//	    			this.returnInfo.setDisable(false);
//	    			this.goHomeBtn.setOpacity(1);
//	    			this.goHomeBtn.setDisable(false);
//	    			this.returnInfo.setText("Return successful");
//	    		}
//				}
//                catch (SQLException e1) {
//                  // TODO Auto-generated catch block
//                  e1.printStackTrace();
//                }
//				}
//			});
		}else {
			disable();
			this.notReturnBike.setOpacity(1);
			this.notReturnBike.setDisable(false);
			this.notReturnBike.setText("You don't rent bike");
		}
		
		this.backBtn.setOnMouseClicked(e -> {
			this.getPreviousScreen().show();
		});
		
		this.okeBtn.setOnMouseClicked(e -> {
			this.okeBtn.setDisable(true);
			this.okeBtn.setOpacity(0);
			this.returnInfo.setDisable(true);
			this.returnInfo.setOpacity(0);
			this.backBtn.setOpacity(0);
			this.backBtn.setDisable(true);
			undisable();
		});
		this.goHomeBtn.setOnMouseClicked(e -> {
			this.goHomeBtn.setDisable(true);
			this.goHomeBtn.setOpacity(0);
			this.returnInfo.setDisable(true);
			this.returnInfo.setOpacity(0);

			homeScreenHandler.show();
		});
	}
	
	private void disable() {
		this.rentIdLabel.setDisable(true);
		this.rentIdLabel.setOpacity(0);
		this.startRentLabel.setDisable(true);
		this.startRentLabel.setOpacity(0);
		this.returnTimeLabel.setDisable(true);
		this.returnTimeLabel.setOpacity(0);
		this.bikeIdLabel.setDisable(true);
		this.bikeIdLabel.setOpacity(0);
		this.rentTypeLabel.setDisable(true);
		this.rentTypeLabel.setOpacity(0);
		this.depositLabel.setDisable(true);
		this.depositLabel.setOpacity(0);
		
		this.rentFeeLabel.setDisable(true);
		this.rentFeeLabel.setOpacity(0);
		this.refundLabel.setDisable(true);
		this.refundLabel.setOpacity(0);
		this.amoutLabel.setDisable(true);
		this.amoutLabel.setOpacity(0);
		this.rentLabel.setDisable(true);
		this.rentLabel.setOpacity(0);
		
		this.startLabel.setDisable(true);
		this.startLabel.setOpacity(0);
		
		this.timeLabel.setDisable(true);
		this.timeLabel.setOpacity(0);
		this.bikeLabel.setDisable(true);
		this.bikeLabel.setOpacity(0);
		this.typeLabel.setDisable(true);
		this.typeLabel.setOpacity(0);
		this.dLabel.setDisable(true);
		this.dLabel.setOpacity(0);
		
		this.reLabel.setDisable(true);
		this.reLabel.setOpacity(0);
		this.refLabel.setDisable(true);
		this.refLabel.setOpacity(0);this.ownLabel.setDisable(true);
		this.ownLabel.setOpacity(0);
		this.caLabel.setDisable(true);
		this.caLabel.setOpacity(0);
		
		this.exLabel.setDisable(true);
		this.exLabel.setOpacity(0);this.seLabel.setDisable(true);
		this.seLabel.setOpacity(0);
		
		this.ownerRField.setDisable(true);
		this.ownerRField.setOpacity(0);
		this.cardNumRField.setDisable(true);
		this.cardNumRField.setOpacity(0);
		this.expiryRField.setDisable(true);
		this.expiryRField.setOpacity(0);
		this.cvvRField.setDisable(true);
		this.cvvRField.setOpacity(0);
		this.returnBtn.setDisable(true);
		this.returnBtn.setOpacity(0);
		this.aLabel.setDisable(true);
		this.aLabel.setOpacity(0);
		this.lineR.setDisable(true);
		this.lineR.setOpacity(0);
	
	}
	
	private void undisable() {
		this.rentIdLabel.setDisable(false);
		this.rentIdLabel.setOpacity(1);
		this.startRentLabel.setDisable(false);
		this.startRentLabel.setOpacity(1);
		this.returnTimeLabel.setDisable(false);
		this.returnTimeLabel.setOpacity(1);
		this.bikeIdLabel.setDisable(false);
		this.bikeIdLabel.setOpacity(1);
		this.rentTypeLabel.setDisable(false);
		this.rentTypeLabel.setOpacity(1);
		this.depositLabel.setDisable(false);
		this.depositLabel.setOpacity(1);
		
		this.rentFeeLabel.setDisable(false);
		this.rentFeeLabel.setOpacity(1);
		this.refundLabel.setDisable(false);
		this.refundLabel.setOpacity(1);
		this.amoutLabel.setDisable(false);
		this.amoutLabel.setOpacity(1);
		this.rentLabel.setDisable(false);
		this.rentLabel.setOpacity(1);
		
		this.startLabel.setDisable(false);
		this.startLabel.setOpacity(1);
		
		this.timeLabel.setDisable(false);
		this.timeLabel.setOpacity(1);
		this.bikeLabel.setDisable(false);
		this.bikeLabel.setOpacity(1);
		this.typeLabel.setDisable(false);
		this.typeLabel.setOpacity(1);
		this.dLabel.setDisable(false);
		this.dLabel.setOpacity(1);
		
		this.reLabel.setDisable(false);
		this.reLabel.setOpacity(1);
		this.refLabel.setDisable(false);
		this.refLabel.setOpacity(1);this.ownLabel.setDisable(false);
		this.ownLabel.setOpacity(1);
		this.caLabel.setDisable(false);
		this.caLabel.setOpacity(1);
		
		this.exLabel.setDisable(false);
		this.exLabel.setOpacity(1);this.seLabel.setDisable(false);
		this.seLabel.setOpacity(1);
		
		this.ownerRField.setDisable(false);
		this.ownerRField.setOpacity(1);
		this.cardNumRField.setDisable(false);
		this.cardNumRField.setOpacity(1);
		this.expiryRField.setDisable(false);
		this.expiryRField.setOpacity(1);
		this.cvvRField.setDisable(false);
		this.cvvRField.setOpacity(1);
		this.returnBtn.setDisable(false);
		this.returnBtn.setOpacity(1);
		this.aLabel.setDisable(false);
		this.aLabel.setOpacity(1);
		this.lineR.setDisable(false);
		this.lineR.setOpacity(1);
	
	}
	
}