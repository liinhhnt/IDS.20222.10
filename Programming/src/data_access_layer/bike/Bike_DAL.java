package data_access_layer.bike;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data_access_layer.db.EcoBikeDB;
import entity.bike.Bike;
import entity.bike.StandardBike;
import entity.bike.StandardEBike;
import entity.bike.TwinsBike;

public class Bike_DAL {
	
	public static Bike createBike(int bikeType) {
	    Bike bike;

	    switch (bikeType) {
	        case StandardBike.BIKE_TYPE_VALUE:
	            bike = new StandardBike();
	            break;
	        case StandardEBike.BIKE_TYPE_VALUE:
	        	bike = new StandardEBike();
	            break;
	        case TwinsBike.BIKE_TYPE_VALUE:
	        	bike = new TwinsBike();
	            break;
	        default:
	            bike = null;
	            break;
	    }

	    return bike;
	}

    public static Bike getBikeById(int bikeId) throws SQLException {
    	
        Connection connection = EcoBikeDB.getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("select * from `bike` where bikeId =  %d", bikeId);
        ResultSet result = statement.executeQuery(query);

        if (result.next()) {
        	Bike bike = createBike(result.getInt("type"));
            bike.setImgUrl(result.getString("imgUrl"));
            bike.setType(result.getInt("type"));
            bike.setDockId(result.getInt("dockId"));
            bike.setBikeId(result.getString("bikeId"));
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
        	Bike bike = createBike(result.getInt("type"));
            bike.setImgUrl(result.getString("imgUrl"));
            bike.setType(result.getInt("type"));
            bike.setDockId(result.getInt("dockId"));
            bike.setBikeId(result.getString("bikeId"));
            bike.setBarCode(result.getString("barCode"));
            bike.setLicensePlate(result.getString("licencePlate"));
            bike.setBeingUsed(result.getBoolean("isBeingUsed"));

            // get other bike Attributes
//            switch (resultSet.getInt("type")) {
//                case StandardEBike.BIKE_TYPE_VALUE:
//                    getEBikeAttribute((StandardEBike) bike);
//                    break;
//
//                default:
//                    break;
//            }
            bikeList.add(bike);
        }

        return bikeList;
    }
    
    public String convertBarcodeToBikeId(String barcode) throws SQLException {

        Connection connection = EcoBikeDB.getConnection();
        Statement statement = connection.createStatement();

        String query = String.format("select bikeId from `bike` where barCode = '%s' ;", barcode);
        ResultSet result = statement.executeQuery(query);
        if (result.next()) {
        	String bikeId = result.getString("bikeId");
        	 return bikeId;
        };
        return null;
    }

}