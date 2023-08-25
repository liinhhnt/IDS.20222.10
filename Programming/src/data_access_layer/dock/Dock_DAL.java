package data_access_layer.dock;

import java.sql.*;
import java.util.ArrayList;

import data_access_layer.bike.Bike_DAL;
import data_access_layer.db.EcoBikeDB;
import entity.bike.Bike;
import entity.dock.Dock;

public class Dock_DAL {

	public Dock_DAL() {
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<Dock> getListDock() throws SQLException {

        Connection connection = EcoBikeDB.getConnection();
        Statement statement = connection.createStatement();

        String sql = "select * from dock";
        ResultSet resultSet = statement.executeQuery(sql);
        
        ArrayList<Dock> docksList = new ArrayList<Dock>();

        while (resultSet.next()) {
            Dock dock = new Dock();
            dock.setDockId(resultSet.getInt("dockId"));
            dock.setName(resultSet.getString("name"));
            dock.setNoOfEmptyPoints(resultSet.getInt("noOfEmptyPoints"));
            dock.setNoOfBikes(resultSet.getInt("noOfBikes"));
            dock.setArea(resultSet.getInt("area"));
            dock.setAddress(resultSet.getString("address"));
            dock.setImageUrl(resultSet.getString("imageUrl"));

            docksList.add(dock);
        }
        return docksList;
    }
	
	public static ArrayList<Dock> getDockByName(String name) throws SQLException {
		Connection connection = EcoBikeDB.getConnection();
        Statement statement = connection.createStatement();

        String sql = String.format("SELECT * FROM `dock` WHERE `name` LIKE '%%%s%%'", name);

        ResultSet resultSet = statement.executeQuery(sql);
        
        ArrayList<Dock> docksList = new ArrayList<Dock>();

        while (resultSet.next()) {
            Dock dock = new Dock();
            dock.setDockId(resultSet.getInt("dockId"));
            dock.setName(resultSet.getString("name"));
            dock.setNoOfEmptyPoints(resultSet.getInt("noOfEmptyPoints"));
            dock.setNoOfBikes(resultSet.getInt("noOfBikes"));
            dock.setArea(resultSet.getInt("area"));
            dock.setAddress(resultSet.getString("address"));
            dock.setImageUrl(resultSet.getString("imageUrl"));

            docksList.add(dock);
        }
        return docksList;
	}
	
	public static ArrayList<Dock> getDockByAddress(String address) throws SQLException {
		Connection connection = EcoBikeDB.getConnection();
        Statement statement = connection.createStatement();

        String sql = String.format("SELECT * FROM `dock` WHERE `address` LIKE '%%%s%%'", address);

        ResultSet resultSet = statement.executeQuery(sql);
        
        ArrayList<Dock> docksList = new ArrayList<Dock>();

        while (resultSet.next()) {
            Dock dock = new Dock();
            dock.setDockId(resultSet.getInt("dockId"));
            dock.setName(resultSet.getString("name"));
            dock.setNoOfEmptyPoints(resultSet.getInt("noOfEmptyPoints"));
            dock.setNoOfBikes(resultSet.getInt("noOfBikes"));
            dock.setArea(resultSet.getInt("area"));
            dock.setAddress(resultSet.getString("address"));
            dock.setImageUrl(resultSet.getString("imageUrl"));

            docksList.add(dock);
        }

        return docksList;
	}
	
	public static ArrayList<Bike> getBikesOfDock(int dockId) {
		ArrayList<Bike> lstBike = null;
		try {
			lstBike = Bike_DAL.getBikeListInDock(dockId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstBike;
	}
	public Dock getInfoDock(int bikeId) throws SQLException {

		Connection connection = EcoBikeDB.getConnection();
        Statement statement = connection.createStatement();

        String query = String.format("select * from bike inner join dock on bike.dock_id = dock.id where bike.id = %d",
                bikeId);
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        Dock dock = new Dock();
        dock.setDockId(resultSet.getInt("id"));
        dock.setName(resultSet.getString("name"));
        dock.setArea(resultSet.getInt("dockArea"));
        dock.setAddress(resultSet.getString("address"));
        dock.setImageUrl(resultSet.getString("dock_image_url"));

        return dock;
    }
	public void updateDockPoint(int dockId) throws SQLException {

		Connection connection = EcoBikeDB.getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("UPDATE dock SET noOfEmptyPoints = noOfEmptyPoints + 1,"
        		+ " noOfBikes = noOfBikes - 1 WHERE dockId = %d ",dockId);
        statement.execute(query);
    }

    public void updateReturnDockPoint(int dockId) throws SQLException {
        Connection connection = EcoBikeDB.getConnection();
        Statement statement = connection.createStatement();
        String query = String.format("UPDATE dock SET noOfEmptyPoints = noOfEmptyPoints - 1,"
        		+ " noOfBikes = noOfBikes + 1 WHERE dockId = %d ",dockId);
        statement.execute(query);
    }


	public static Dock getInfoDockByDockId(int dockId) throws SQLException {
		Connection connection = EcoBikeDB.getConnection();
        Statement statement = connection.createStatement();
        
        String query = String.format("select * from dock where dockId = %d", dockId);
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        Dock dock = new Dock();
        dock.setDockId(resultSet.getInt("dockId"));
        dock.setName(resultSet.getString("name"));
        dock.setNoOfEmptyPoints(resultSet.getInt("noOfEmptyPoints"));
        dock.setNoOfBikes(resultSet.getInt("noOfBikes"));
        dock.setArea(resultSet.getInt("area"));
        dock.setAddress(resultSet.getString("address"));
        dock.setImageUrl(resultSet.getString("imageUrl"));
		return dock;
	}
}