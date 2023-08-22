package view.screen.rentingbike;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import common.exception.EcoBikeRentalException;
import data_access_layer.bike.Bike_DAL;
import entity.bike.Bike;
import javafx.stage.Stage;
import utils.Configs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import view.screen.BaseScreenHandler;

public class RentalListHandler extends BaseScreenHandler {

	@FXML
	private Button backBtn;

	@FXML
	private VBox bikeListVbox;

	public RentalListHandler(Stage stage, String screenPath) throws IOException, SQLException {
		super(stage, screenPath);
		backBtn.setOnMouseClicked(e -> {
			this.getPreviousScreen().show();
		});
		showRentalList();
	}

	private void showRentalList() throws SQLException {
		ArrayList<Bike> bikeList = null;
		try {
			bikeList = Bike_DAL.getBikeBeingUsed();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bikeListVbox.getChildren().clear();
		try {
			displayBikes(bikeList);
		} catch (IOException exception) {
			throw new EcoBikeRentalException(exception.getMessage());
		}
	}

	private void displayBikes(ArrayList<Bike> bikeList) throws IOException, SQLException {
		for (Bike bike : bikeList) {
			RentalBikeInList bikeInListHandler = new RentalBikeInList(Configs.BIKE_IN_DOCK_PATH, this);
			bikeInListHandler.setBike(bike);
			bikeInListHandler.setBikeInfo();
			bikeListVbox.getChildren().add(bikeInListHandler.getContent());
		}
	}
}
