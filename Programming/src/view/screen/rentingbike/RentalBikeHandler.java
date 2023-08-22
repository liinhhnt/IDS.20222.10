package view.screen.rentingbike;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import common.exception.EcoBikeRentalException;
import controller.rent_bike.RentBikeController;
import data_access_layer.bike.Bike_DAL;
import entity.bike.Bike;
import entity.rent_bike.RentInfo;
import entity.bike.StandardEBike;
import entity.dock.Dock;
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
import utils.Utils;
import utils.Configs;
import view.screen.BaseScreenHandler;
import view.screen.FXMLScreenHandler;

public class RentalBikeHandler extends FXMLScreenHandler{
	@FXML
	private Label barcode, typeBike, battery, deposit, licensePlate, pedals, remainingTime, minutes, seconds, hours  ;
	@FXML
	private ImageView image;
	@FXML
	private Button returnBike, backBtn;
	public RentalBikeHandler() {
		// TODO Auto-generated constructor stub
	}
}
