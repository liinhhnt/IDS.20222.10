package view.screen.bike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.view.ViewBikeController;
import entity.bike.Bike;
import entity.bike.BikeType;
import entity.bike.StandardEBike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.screen.BaseScreenHandler;
import utils.Utils;

public class BikeViewHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private Label barcode, typeBike, brandBike, licensePlates,saddles, rearSeats, pedals, deposit;

    @FXML
    private Label battery, remainingTime, remainingTimeLabel, batteryLabel;

    @FXML
    private ImageView image;

    @FXML
    private Button rentBtn;

    private final ViewBikeController viewBikeController = new ViewBikeController();
    private Bike bike;
    private BikeType bikeType;

    // private StandardEBike standardEBike;
    public BikeViewHandler(Stage stage, String screenPath, Bike bike, BikeType bikeType) throws IOException {
        super(stage, screenPath);
        this.bike = bike;
        this.bikeType = bikeType;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        barcode.setText(bike.getBarCode());
    	typeBike.setText(bikeType.getName());
        licensePlates.setText(bike.getLicensePlate());
        saddles.setText(""+bikeType.getNoSaddles());
        pedals.setText(""+bikeType.getNoPedals());
        rearSeats.setText(""+bikeType.getNoSaddles());
        deposit.setText(""+Utils.calculateDeposit(bikeType.getValue()));
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
    }

    @FXML
    public void handleReturn(MouseEvent event) {
        getPreviousScreen().show();
    }

    // E-bike display
    private void setEBikeAttrData() {
        StandardEBike eBike;
        batteryLabel.setVisible(true);
        battery.setVisible(true);

        eBike = (StandardEBike) bike;
        battery.setText(new String(eBike.getBateryPercent() + "%"));
    }
}