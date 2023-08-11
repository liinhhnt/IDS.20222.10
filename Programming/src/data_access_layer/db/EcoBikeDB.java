package data_access_layer.db;

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
      System.out.println("Connect to database successfully");
//      Statement stmt = connection.createStatement();
//      String sql = "INSERT INTO `bike` VALUES (1,1,'S001','99-G1 12345',false,'https://www.bad-bike.it/wp-content/uploads/2022/03/EVO_FAT_Green_Flou.jpg', 1), (2,2,'SE001','99-G1 12345',false,'https://www.bad-bike.it/wp-content/uploads/2022/03/EVO_FAT_Green_Flou.jpg', 1), (3,3,'T001','99-G1 12345',false,'https://www.bad-bike.it/wp-content/uploads/2022/03/EVO_FAT_Green_Flou.jpg', 2);";
////      String sql = "INSERT INTO `dock` VALUES (1,'Hust Station','1st Dai Co Viet Street', 1000, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),(2,'Hoan Kiem Station','Trang Tien Street', 999,'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw'),(3,'To Hoang','67 To Hoang Lane', 500, 'https://th.bing.com/th/id/OIP.DfcFX9B00Y6I5Uw9Ckx5TQHaE8?w=288&h=192&c=7&r=0&o=5&dpr=1.6&pid=1.7&fbclid=IwAR0_LME57QWeJYPn1wvs_yI5lQd3PdnlZSUpy6ElbS3rl7-WbduUkpNl8rw');";
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

