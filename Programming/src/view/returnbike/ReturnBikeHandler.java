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

import controller.return_bike.ReturnBikeController;
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

public class ReturnBikeHandler extends BaseScreenHandler{
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
	}
}