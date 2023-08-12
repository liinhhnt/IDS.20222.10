package enitity.invoice;

import java.sql.Date;
import java.sql.SQLException;

import javax.print.event.PrintJobAttributeEvent;

import com.mysql.cj.xdevapi.Statement;

import calculate.CalculateFee;
import calculate.ICalculator;
import data_access_layer.bike.Bike_DAL;
import data_access_layer.db.EcoBikeDB;
import entity.bike.Bike;

public class Invoice {
	private Bike bike;
	private int id;
	private Date startTimeDate;
	private int totalTime;
	private int totalMoney;
	private int depositFee;
	private int status = 0;		//indicate invoice is paid or not
	
	private static Invoice rentInvoice;
	
	public static Invoice getRentInvoice() {
		if(rentInvoice == null)
			rentInvoice = new Invoice();
		
		return rentInvoice;
	}
	
	//get rent bike fee
	public String getRentFeeInfo() {
		ICalculator calculator = new CalculateFee();
		return calculator.getInfoRental(bike.getType());
	}
	
//	public void saveRentInvoice() throws SQLException{
//		Statement stmStatement = EcoBikeDB.getConnection().createStatement();
//		String queryString = String.format("update invoice ", null)
//	}
}
