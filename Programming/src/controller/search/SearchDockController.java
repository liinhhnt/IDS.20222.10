package controller.search;

import java.sql.SQLException;
import java.util.ArrayList;

import common.exception.InvalidSearchKeyException;
import common.exception.NoResultException;
import controller.BaseScreenController;
import entity.dock.Dock;

/**
 * This class is the search engine to search for dock.
 *
 * @author ntlinh
 *
 */
public abstract class SearchDockController extends BaseScreenController {
	/**
	 * This method search for docks that matched given keyword.
	 *
	 * @param key the given search keyword
	 * @return ArrayList<Dock> list of result dock
	 * @throws NoResultException throws if there is no matching dock
	 * @throws SQLException throws if error occurs during query
	 */
  public abstract ArrayList<Dock> searchDock(String key) throws NoResultException, InvalidSearchKeyException, SQLException;
  
  /**
   * This method validates search key inputed from user.
   *
   * @param key the search key that user entered
   * @throws InvalidSearchKeyException throws if the given search key is invalid(empty/full whitespace) 
   */
  public void validateSearchKey(String key) throws InvalidSearchKeyException {
	  if(key.isBlank()) {
		  throw new InvalidSearchKeyException("Empty search key! Please type something to search!!!");
	  }
  }
}