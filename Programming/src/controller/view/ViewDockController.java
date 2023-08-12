package controller.view;

import java.sql.SQLException;
import java.util.ArrayList;
import controller.BaseScreenController;
import data_access_layer.bike.BikeType_DAL;
import data_access_layer.bike.Bike_DAL;
import entity.bike.Bike;
import entity.bike.BikeType;

/**
 * Controller class for view dock dock use case.
 *
 * @author ntlinh
 *
 */
public class ViewDockController extends BaseScreenController {
	/**
	 * This method get list of bikes in dock to to dock info
	 *
	 * @param key the dockID for view dock
	 * @return ArrayList<Bike> list of bikes in dock
	 * @throws SQLException throws if error occurs during query
	 */
  public static ArrayList<Bike> requestViewBikes(int dockId) throws SQLException {
    // TODO
    ArrayList<Bike> bikelist = Bike_DAL.getBikeListInDock(dockId);
    return bikelist;
  }
  
  public static BikeType getBikeType(int type) throws SQLException {
	  BikeType bikeType = BikeType_DAL.getById(type);
	  return bikeType;
  }
}
