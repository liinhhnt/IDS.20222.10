package controller.view;

import java.sql.SQLException;
import java.util.ArrayList;
import controller.BaseScreenController;
import data_access_layer.bike.Bike_DAL;
import entity.bike.Bike;

/**
 * Controller class for view dock dock use case.
 *
 * @author ntlinh
 *
 */
public class ViewBikeController extends BaseScreenController {
	/**
	 * This method view bike
	 *
	 * @param key the bikeId for view bike
	 * @return Bike match
	 * @throws SQLException throws if error occurs during query
	 */
  public Bike requestViewBikes(int bikeId) throws SQLException {
    // TODO
    Bike bike = Bike_DAL.getBikeById(bikeId);
    return bike;
  }
}