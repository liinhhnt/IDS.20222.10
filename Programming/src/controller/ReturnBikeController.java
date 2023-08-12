package controller;

/*
 * This class is return bike controller, to handle logic usecase return bike
 * author: Linh.pk
 */

import java.sql.SQLException;
import java.util.ArrayList;

import data_access_layer.bike.Bike_DAL;
import data_access_layer.dock.Dock_DAL;
import entity.bike.Bike;
import entity.bike.BikeType;
import entity.dock.Dock;


public class ReturnBikeController extends BaseScreenController{
	//ham xu li database
	//private ReturnBike_BL returnBike_BL = new ReturnBike_BL();
	
	private final Bike_DAL bike_DAL = new Bike_DAL();
	
	private final Dock_DAL dock_DAL = new Dock_DAL();
	
	public ReturnBikeController() {
		// TODO Auto-generated constructor stub
	}
	
	public void getListDock(ArrayList<Dock> dockList) throws Exception{
		
	}
	
}
