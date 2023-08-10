//package entity.dock;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
////import entity.bike.Bike;
////import entity.bike.Ebike;
////import entity.db.EcoDB;
//
///**
// * Represent dock entity.
// *
// * @author ntlinh
// *
// */
//public class Dock {
//	  private int id;
//	  private String address;
//	  private String name;
//	  private int maxSlot;
//	  private int avaiSlot;
//	  private int area;
//	  private String imageUrl;
//	  private static ArrayList<Dock> docklist;
//	  
//	  
//	  public Dock(int id, String address, String name, int maxSlot, int availSlot, int area, String imgUrl) {
//	    this.id = id;
//	    this.address = address;
//	    this.name = name;
//	    this.maxSlot = maxSlot;
//	    this.avaiSlot = availSlot;
//	    this.area = area;
//	    this.imageUrl = imgUrl;
//	  }
//	  
//	  /**
//		 * This method get for all dock in system.
//		 *
//		 * @return ArrayList<Dock> list of dock
//		 * @throws SQLException throws if error occurs during query
//		 */
//	  public static ArrayList<Dock> getAllDocks() throws SQLException{
//		if(docklist == null) {
//		    docklist = new ArrayList<>();
//		    Statement stm = EcoDB.getConnection().createStatement();
//		    String query = "select * from docks order by (id) asc;";
//		    ResultSet res = stm.executeQuery(query);
//		    while(res.next()) {
//		      docklist.add(new Dock(res.getInt("id"),
//		                            res.getString("address"),
//		                            res.getString("name"),
//		                            res.getInt("max_capacity"),
//		                            res.getInt("avail_slot"),
//		                            res.getInt("area"),
//		                            res.getString("image_url")));
//		      
//		    }
//		}
//	    return docklist;
//	  }
//	  /**
//		 * This method get for all bike in material dock.
//		 *
//		 * @return ArrayList<Bike> list of dock
//		 * @throws SQLException throws if error occurs during query
//		 */
//	  public ArrayList<Bike> getBikes() throws SQLException{
//	    return Bike.getBikesInDock(this.id);
//	  }
//	  /**
//		 * This method check available empty slot in dock .
//		 *
//		 * @return boolean (true if full slot)
//		 */
//	  public boolean isFull() {
//		return this.getMaxSlot()==this.getAvaiSlot();
//	  }
//	  
//	  //----------Set get------------------ 
//	  
//	  public int getId() {
//	    return id;
//	  }
//	
//	  public int getAvaiSlot() {
//	    return avaiSlot;
//	  }
//	
//	  public void setAvaiSlot(int avaiSlot) {
//	    this.avaiSlot = avaiSlot;
//	  }
//	
//	  public String getAddress() {
//	    return address;
//	  }
//	
//	  public String getName() {
//	    return name;
//	  }
//	
//	  public int getMaxSlot() {
//	    return maxSlot;
//	  }
//	  
//	  public int getArea() {
//	    return area;
//	  }
//	  
//	  public String getImageUrl() {
//		return this.imageUrl;  
//	  }
//	  
//	  /**
//		 * This method check success for return bike.
//		 *
//		 * @param bike
//		 * @return boolean (true if return bike successfully)
//	 * @throws SQLException 
//		 */
//	  public boolean returnBikeInDock(Bike bike) throws SQLException {
//		  Statement stm = EcoDB.getConnection().createStatement();
//		  String query = String.format("update bikes set dock_id = %d where id = %d;", this.id, bike.getId());
//		  stm.executeUpdate(query);
//		  query = String.format("update docks "
//		  					  + "set avail_slot = (select avail_slot from docks where id = %d) + 1 "
//		  					  + "where id = %d;", 
//		  					  this.id, this.id);
//		  return true;
//	  } 
//	  
//	  /**
//		 * This method check success for rent bike.
//		 *
//		 * @param bike
//		 * @return boolean (true if rent bike successfully)
//		 * @throws SQLException 
//		 */
//	  public boolean rentBikeFromDock(Bike bike) throws SQLException {
//	      Statement stm = EcoDB.getConnection().createStatement();
//	      String query = String.format("update bikes set dock_id = null where id = %d;", bike.getId());
//	      stm.executeUpdate(query);
//	      query = String.format("update docks "
//	      					  + "set avail_slot = (select avail_slot from docks where id = %d) - 1 "
//	      					  + "where id = %d", 
//	      					  this.id, this.id);
//	      stm.executeUpdate(query);
//		  return true;
//	  }
//	  
//	  /**
//		 * This method get bike from dock match the bikecode.
//		 *
//		 * @param bikecode
//		 * @return Bike
//		 */
//	  public Bike getBike(String bikecode) {
//		  Bike bike = null;
//		  try {
//			bike = Bike.getBikeByBikecode(bikecode, this.id);
//		  } catch (SQLException e) {
//			System.out.println(e.getMessage());
//		  }
//		  return bike;
//	  }  
//}