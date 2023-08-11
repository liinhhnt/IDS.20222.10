package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import common.exception.InvalidSearchKeyException;
import common.exception.NoResultException;
import controller.search.SearchDockController;
import data_access_layer.dock.Dock_DAL;
import controller.search.SearchDockByNameController;
import entity.dock.Dock;

/**
 * This class controls the flow of events in homeScreen
 * @author ntlinh
 *
 */
public class HomeScreenController extends BaseScreenController {
  private SearchDockController searchDockController;
  
  public HomeScreenController() {
	  this.searchDockController = new SearchDockByNameController(); // set default search mode = by name
  }
  
  /**
   * this method set search controller
   * @param searchDockController
   */
  public void setSearchController(SearchDockController searchDockController) {
	  this.searchDockController = searchDockController;
  }
  
  /**
   * This method search for docks match user key.
   * @param key the search key user enter
   * @return ArrayList<Dock> list of the matching docks if there is any
   * @throws InvalidSearchKeyException if user input blank key
   * @throws NoResultException if there is no matching dock found
   * @throws SQLException throws if error occurs during query
   */
  public ArrayList<Dock> search(String key) throws InvalidSearchKeyException, NoResultException, SQLException {
	  ArrayList<Dock> result = searchDockController.searchDock(key);
	  return result;
  }
  
  public static ArrayList<Dock> getAllDocks() {
	  ArrayList<Dock> lstDocks = null;
	try {
		lstDocks = Dock_DAL.getListDock();
		  return lstDocks;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return lstDocks;
  }
}