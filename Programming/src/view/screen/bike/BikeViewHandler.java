package view.screen.bike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import calculate.CalculateFee;
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
import utils.Configs;
import utils.Utils;
import view.screen.payment.InputCardHandler;
public class BikeViewHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private Label barcode, typeBike, brandBike, licensePlate,saddles, rearSeats, pedals, deposit;

    @FXML
    private Label battery, remainingTime, remainingTimeLabel, batteryLabel;

    @FXML
    private ImageView image;

    @FXML
    private Button rentBtn;
    
    @FXML 
    private Button backBtn;

    private final ViewBikeController viewBikeController = new ViewBikeController();
    private Bike bike;
    private BikeType bikeType;
    private int depo;
    public BikeViewHandler(Stage stage, String screenPath, Bike bike, BikeType bikeType) throws IOException {
        super(stage, screenPath);
        this.bike = bike;
        this.bikeType = bikeType;
        barcode.setText(bike.getBarCode());
    	typeBike.setText(bikeType.getName());
        licensePlate.setText(bike.getLicensePlate());
        saddles.setText(""+bikeType.getNoSaddles());
        pedals.setText(""+bikeType.getNoPedals());
        rearSeats.setText(""+bikeType.getNoSaddles());
        CalculateFee calculateFee = new CalculateFee();
        try {
			this.depo = calculateFee.calculateDepositFee(bikeType.getValue());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        deposit.setText(""+depo);
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	backBtn.setOnMouseClicked(e -> {
			this.getPreviousScreen().show();
		});
    	rentBtn.setOnMouseClicked(e -> {
    		try {
				InputCardHandler inputCard = new InputCardHandler(this.getStage(), Configs.INPUT_CARD_SCREEN_PATH, bike, depo);
				inputCard.setPreviousScreen(this);
				inputCard.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	});
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
        remainingTime.setVisible(true);
        remainingTimeLabel.setVisible(true);

        eBike = (StandardEBike) bike;
        battery.setText(""+eBike.getBatteryPercent() + "%");
        remainingTime.setText("" + eBike.getRemainingTime());
    }
}