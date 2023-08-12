package view.screen.bike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.view.ViewBikeController;
import entity.bike.Bike;
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

public class BikeViewHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private Label barcode, typeBike, brandBike, licensePlates, deposit;

    @FXML
    private Label availableTime, battery, tgkd, batteryLabel;

    @FXML
    private ImageView image;

    @FXML
    private Button returnButton;

    private final ViewBikeController viewController = new ViewBikeController();
    private Bike bike;

    // private StandardEBike standardEBike;
    public BikeViewHandler(String screenPath, Stage stage, Bike bike) throws IOException {
        super(screenPath, stage);
        this.bike = bike;
        this.initialize();
    }

    @Override
    private void initialize(URL arg0, ResourceBundle arg1) {
        typeBike.setText(utlis.Helper.convertToStringBikeType(bike.getBikeType()));
        brandBike.setText(bike.getBrand());
        licensePlates.setText(bike.getLicensePlate());
        deposit.setText(Integer.toString(bike.getBikeValue()));
        // set image
        Image imageLink = new Image(bike.getBikeImageUrl());
        image.setImage(imageLink);
        image.setPreserveRatio(false);

        battery.setVisible(false);
        batteryLabel.setVisible(false);

        switch (bike.getBikeType()) {
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