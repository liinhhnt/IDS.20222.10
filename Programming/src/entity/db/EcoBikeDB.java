package entity.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


//import entity.bike.Bike;
//import entity.bike.Ebike;
//import entity.invoice.Invoice;
import utils.Configs;

/**
 * Responsible for connection to database.
 *
 * @author ntlinh
 *
 */
public class EcoBikeDB {
  private static Connection connection = null;
  
  private static final String className = "com.mysql.cj.jdbc.Driver";
  	/**
	 * This method get connect to database
	 */
  public static Connection getConnection() {
    if(connection != null) {
      return connection;
    }
    try {
      Class.forName(className);
      connection = DriverManager.getConnection(Configs.DB_URL, Configs.DB_USERNAME, Configs.DB_PASSWORD);
//      Statement stmt = connection.createStatement();
      System.out.println("Connect to database successfully");
//      String sql = "INSERT INTO `bike` VALUES (1,1,'S001','99-G1 12345',400000,false,'https://www.bad-bike.it/wp-content/uploads/2022/03/EVO_FAT_Green_Flou.jpg', 1);";
//      stmt.executeUpdate(sql);
//      System.out.println("Record inserted!");
    } catch(Exception e) {
      e.printStackTrace();
    }
    return connection;
  }
  
 
 
  public static void main(String[] args) {
    EcoBikeDB.getConnection();
  }
}

