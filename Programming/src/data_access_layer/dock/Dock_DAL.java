package data_access_layer.dock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
//       
		public static void main(String[] args) throws SQLException {
//			 Connection connection = EcoBikeDB.getConnection();
//	        Statement statement = connection.createStatement();
//	        String sql = "INSERT INTO `dock` VALUES (1,'Hust Station','1st Dai Co Viet Street', 100, 0, 1000, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),(2,'Hoan Kiem Station','Trang Tien Street', 50, 0, 999,'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),(3,'To Hoang','67 To Hoang Lane', 30, 0, 500, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw');";
//	        statement.executeUpdate(sql);
//	        System.out.println("Record inserted!");
			
//		    ArrayList<Dock> dockList = null;
//			try {
//				dockList = getDockByAddress("67");
//				if (dockList != null && !dockList.isEmpty()) {
//				    for (Dock dock : dockList) {
//				        System.out.println("Dock ID: " + dock.getDockId());
//				        System.out.println("Name: " + dock.getName());
//				        System.out.println("Number of empty points: " + dock.getNoOfEmptyPoints());
//				        System.out.println("Number of bikes available: " + dock.getNoOfBikes());
//				        System.out.println("Area: " + dock.getArea());
//				        System.out.println("Address: " + dock.getAddress());
//				        System.out.println("Image URL: " + dock.getImageUrl());
//				        System.out.println("-----------------------");
//				    }
//				}
//				else {
//					System.out.println("No dock");
//				}
//			} catch (Exception e) {
//				System.out.println("No docks found.");
//				e.printStackTrace();
//			}
			
//			try {
//				Bike bike = Bike_DAL.getBikeById(1);
//				System.out.print(bike.getBarCode());
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
//			ArrayList<Bike> bikeList = null;
//			try {
//				bikeList = getBikesOfDock(1);
//				if (bikeList != null && !bikeList.isEmpty()) {
//				    for (Bike bike : bikeList) {
//				        System.out.println("Bike ID: " + bike.getBikeId());
//				        System.out.println("Type: " + bike.getType());
//				        System.out.println("Barcode: " + bike.getBarCode());
//				        System.out.println("Status: " + bike.getIsBeingUsed());
//				        System.out.println("Lisence Plate: " + bike.getLicensePlate());
//				        System.out.println("Image URL: " + bike.getImgUrl());
//				        System.out.println("-----------------------");
//				    }
//				}
//				else {
//					System.out.println("No bike");
//				}
//			} catch (Exception e) {
//				System.out.println("No bike found.");
//				e.printStackTrace();
//			}
		}
	
}
