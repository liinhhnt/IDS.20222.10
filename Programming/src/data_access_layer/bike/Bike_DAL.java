package data_access_layer.bike;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import data_access_layer.database.Database;
import data_access_layer.db.EcoBikeDB;
import entity.bike.Bike;
import entity.bike.StandardBike;
import entity.bike.StandardEBike;
import entity.bike.TwinsBike;

public class Bike_DAL {

	public static String getByTypeString(int type) throws SQLException {
		Connection connection = EcoBikeDB.getConnection();
		Statement statement = connection.createStatement();
		String query = String.format("select name from `bikeType` where typeId =  %d", type);
		ResultSet result = statement.executeQuery(query);
		if (result.next()) {
			return result.getString("name");
		} else
			return null;
	}

	public static int getBikeValue(int type) throws SQLException {
		Connection connection = EcoBikeDB.getConnection();
		Statement statement = connection.createStatement();
		String query = String.format("select value from `bikeType` where typeId =  %d", type);
		ResultSet result = statement.executeQuery(query);
		if (result.next()) {
			return result.getInt("value");
		} else
			return 0;
	}

	public static Bike getBikeById(int bikeId) throws SQLException {

		Connection connection = EcoBikeDB.getConnection();
		Statement statement = connection.createStatement();
		String query = String.format("select * from `bike` where bikeId =  %d", bikeId);
		ResultSet result = statement.executeQuery(query);

		if (result.next()) {
			Bike bike = Bike.createBike(result.getInt("type"));
			bike.setImgUrl(result.getString("imgUrl"));
			bike.setType(result.getInt("type"));
			bike.setDockId(result.getInt("dockId"));
			bike.setBikeId(result.getInt("bikeId"));
			bike.setBarCode(result.getString("barCode"));
			bike.setLicensePlate(result.getString("licencePlate"));
			bike.setBeingUsed(result.getBoolean("isBeingUsed"));

			return bike;
		}
		return null;
	}

	public static ArrayList<Bike> getBikeListInDock(int dock_id) throws SQLException {
		ArrayList<Bike> bikeList = new ArrayList<Bike>();

		Connection connection = EcoBikeDB.getConnection();
		Statement statement = connection.createStatement();

		String query = String.format("select * from `bike` where dockId = %d and isBeingUsed = false", dock_id);
		ResultSet result = statement.executeQuery(query);
		while (result.next()) {
			Bike bike = Bike.createBike(result.getInt("type"));
			bike.setImgUrl(result.getString("imgUrl"));
			bike.setType(result.getInt("type"));
			bike.setDockId(result.getInt("dockId"));
			bike.setBikeId(result.getInt("bikeId"));
			bike.setBarCode(result.getString("barCode"));
			bike.setLicensePlate(result.getString("licencePlate"));
			bike.setBeingUsed(result.getBoolean("isBeingUsed"));

			// get other attribute of e-bike
			switch (result.getInt("bikeId")) {
			case StandardEBike.BIKE_TYPE_VALUE:
				getEBikeAttribute((StandardEBike) bike);
				break;
			default:
				break;
			}

			bikeList.add(bike);
		}

		return bikeList;
	}

	public int convertBarcodeToBikeId(String barcode) throws SQLException {

		Connection connection = EcoBikeDB.getConnection();
		Statement statement = connection.createStatement();

		String query = String.format("select bikeId from `bike` where barCode = '%s' ;", barcode);
		ResultSet result = statement.executeQuery(query);
		if (result.next()) {
			int bikeId = result.getInt("bikeId");

			return bikeId;
		}
		;
		return 0;
	}

	public void updateBikeStatus(int bikeId, int isBeingUsed) throws SQLException {
		Connection connection = EcoBikeDB.getConnection();
		Statement statement = connection.createStatement();
		String query = String.format("UPDATE bike SET isBeingUsed = %b WHERE bikeId = %d", isBeingUsed, bikeId);

		statement.executeUpdate(query);
	}

	public static void getEBikeAttribute(StandardEBike eBike) throws SQLException {
		Connection connection = EcoBikeDB.getConnection();
		Statement statement = connection.createStatement();

		String query = String.format("select * from(ebike) where bikeId = %d", eBike.getBikeId());
		ResultSet resultSet = statement.executeQuery(query);

		while (resultSet.next()) {
			eBike.setBatteryPercent(resultSet.getInt("battery"));
			eBike.setRemainingTime(resultSet.getTime("remainingTime"));
		}
	}

}