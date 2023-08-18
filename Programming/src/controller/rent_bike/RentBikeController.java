package controller.rent_bike;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.BaseScreenController;
import entity.bike.Bike;
import entity.dock.Dock;
import data_access_layer.bike.Bike_DAL;
import data_access_layer.dock.Dock_DAL;

public class RentBikeController extends BaseScreenController {


	private final Bike_DAL bike_DAL = new Bike_DAL();

	private final Dock_DAL dock_DAL = new Dock_DAL();

	public int convertBarcodeToBikeId(String barcode) throws SQLException {
		return bike_DAL.convertBarcodeToBikeId(barcode);
	}

	public Bike getBikeByBikeId(int bikeId) throws SQLException {
		return Bike_DAL.getBikeById(bikeId);
	}

	public Dock getDockInfo(int bikeId) throws SQLException {
		return dock_DAL.getInfoDock(bikeId);
	}

	public int getDeposit(int bikeValue) {
		return utils.Utils.getDepositeAmount(bikeValue);
	}

	public void updateAfterRentBike(int bikeId, int bikeType) throws SQLException {
		bike_DAL.updateBikeStatus(bikeId, utils.Constant.IS_BEING_USED);
		dock_DAL.updateDockPoint(bikeId, bikeType, 1);
	}
}