package view.screen.rentbike;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import common.exception.EcoBikeRentalException;
import controller.rent_bike.RentBikeController;
import entity.bike.Bike;
import entity.rent_bike.RentInfo;
import entity.bike.StandardEBike;
import entity.dock.Dock;
import entity.transaction.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Utils;
import utils.Configs;
import view.screen.BaseScreenHandler;
import view.handler.payment.InsertCardScreenHandler;

public class RentBikeHandler extends BaseScreenHandler {
    private static Logger LOGGER = Utils.getLogger(RentBikeHandler.class.getName());
    private Bike bike;

    private String barcode;

    private RentInfo bikeRentInfo = new RentInfo();

    @FXML
    private ImageView image;

    @FXML
    private Label nameDock, address, bikeType, brand, licensePlate, deposit, barcodelb, bateryTitle, bateryPercent;

    private static RentBikeController rentBikeController = new RentBikeController();

    public RentBikeHandler(String screenPath, Stage stage, Bike bike, String Barcode)
            throws IOException, SQLException {
        super(stage, screenPath);
        this.bike = bike;
        this.barcode = Barcode;
        bikeRentInfo.setBike(bike);
        this.initialize();
    }

    private void initialize() throws SQLException {

        Dock dock = rentBikeController.getDockInfo(bike.getBikeId());
        nameDock.setText(dock.getName());
        address.setText(dock.getAddress());
        bikeType.setText(Utils.convertToStringBikeType(bike.getType()));
        licensePlate.setText(bike.getLicensePlate());
        deposit.setText(Integer.toString(rentBikeController.getDeposit(bike.getType())));
        barcodelb.setText(this.barcode);

        // set image
        Image imageLink = new Image(bike.getImgUrl());
        image.setImage(imageLink);
        image.setPreserveRatio(false);

        bateryTitle.setVisible(false);
        bateryPercent.setVisible(false);

        switch (bike.getType()) {
            case StandardEBike.BIKE_TYPE_VALUE:
                setEBikeAttrData();
                break;
            default:
                break;
        }
    }

    @FXML
    public void confirmToRentBike(MouseEvent event) throws IOException {
        LOGGER.info("Confirm to rent bike");
        InsertCardScreenHandler insertCardScreenHandler = new InsertCardScreenHandler(Configs.INSERT_CARD_SCREEN_PATH,
                this.stage, Transaction.RENT, bikeRentInfo);
        insertCardScreenHandler.setPreviousScreen(this);
        insertCardScreenHandler.setHomeScreenHandler(homeScreenHandler);
        insertCardScreenHandler.setScreenTitle("Payment - Confirm to pay");
        insertCardScreenHandler.show();
    }

    private void setEBikeAttrData() {
        StandardEBike eBike;
        bateryTitle.setVisible(true);
        bateryPercent.setVisible(true);
        eBike = (StandardEBike) bike;
        bateryPercent.setText(new String(eBike.getBateryPercent() + "%"));
    }

    @FXML
    public void handleReturn(MouseEvent event) {
        getPreviousScreen().show();
    }
}
