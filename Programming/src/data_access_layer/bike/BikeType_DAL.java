package data_access_layer.bike;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data_access_layer.db.EcoBikeDB;
import entity.bike.BikeType;
public class BikeType_DAL {

	public BikeType_DAL() {
		// TODO Auto-generated constructor stub
	}
	
	 public static BikeType getById(int typeId) throws SQLException {
	    	
	        Connection connection = EcoBikeDB.getConnection();
	        Statement statement = connection.createStatement();
	        String query = String.format("select * from `bikeType` where typeId =  %d", typeId);
	        ResultSet result = statement.executeQuery(query);

	        if (result.next()) {
	        	BikeType bikeType = new BikeType();
	        	bikeType.setTypeId(result.getInt("typeId"));
	        	bikeType.setName(result.getString("name"));
	        	bikeType.setNoPedals(result.getInt("noPedals"));
	        	bikeType.setNoSaddles(result.getInt("noSaddles"));
	        	bikeType.setNoRearSeats(result.getInt("noRearSeats"));
	        	bikeType.setValue(result.getInt("value"));

	            return bikeType;
	        }
	        return null;
	    }

}
