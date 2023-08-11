package controller.search;

import java.sql.SQLException;
import java.util.ArrayList;

import common.exception.InvalidSearchKeyException;
import common.exception.NoResultException;
import data_access_layer.dock.Dock_DAL;
import entity.dock.Dock;


public class SearchDockByNameController extends SearchDockController {
	/**
	  * This method search for a dock by dock name.
	   *
	   * @param key the search key user enter
	   * @return ArrayList<Dock> list of the matching dock if there is any
	   * @throws InvalidSearchKeyException if user input blank key
	   * @throws NoResultException if no result matched
	   * @throws SQLException throws if error occurs during query 
	 */
  @Override
  public ArrayList<Dock> searchDock(String name) throws InvalidSearchKeyException, NoResultException, SQLException {
    validateSearchKey(name);
    ArrayList<Dock> lstDock = Dock_DAL.getDockByName(name);
    if (lstDock == null || lstDock.isEmpty()) 
    	throw new NoResultException(String.format("No dock match with name %s", name));
    else return lstDock;
  }
}