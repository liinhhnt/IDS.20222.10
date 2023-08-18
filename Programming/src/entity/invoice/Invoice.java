package entity.invoice;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Map;

import calculate.CalculateFee;
import calculate.ICalculator;
import entity.bike.Bike;
import utils.Utils;

public class Invoice{
	private Bike bike;
	private int invoiceId;
	private Date startTime;
	private int totalTime;
	private int totalMoney;
	private int depositFee;
	private int status = 0;		//indicate invoice is paid or not
	
	public Invoice (Bike bike, Date startTime, int depositeFee) {
		this.bike = bike;
		this.depositFee = depositeFee;
		this.startTime = startTime;
	}
	
//	private static Invoice rentInvoice;
	
	
//	public static Invoice getRentInvoice() {
//		if(rentInvoice == null)
//			rentInvoice = new Invoice();
//		
//		return rentInvoice;
//	}
	
	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTimeDate) {
//		Random rand = new Random();
//		this.invoiceId = rand.nextInt(1000000)+1;
		this.startTime = startTimeDate;
	}

	public int getTotalTime() {
		int totalTime = Utils.getDifferenceTimes(this.getStartTime(), new java.util.Date());
		setTotalTime(totalTime);
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		
		this.totalTime = totalTime;
	}

	public int getTotalMoney() {
		ICalculator calculator = new CalculateFee();
		int rentime = getTotalTime();
		int fee = calculator.calculateRentFee(bike.getType(), rentime);
		setTotalMoney(fee);
		return fee;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getDepositFee() throws SQLException {
		ICalculator calculator = new CalculateFee();
		return calculator.calculateDepositFee(bike.getType());
	}

	public void setDepositFee(int depositFee) {
		this.depositFee = depositFee;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isRenting() {
		return (this.status==1);
	}

	//get rent bike fee
	public String getRentFeeInfo() {
		ICalculator calculator = new CalculateFee();
		return calculator.getInfoRental(bike.getType());
	}
	
	/**
	 * This method get for invoice information
	 *
	 * @return Map<String, String> list of result message for invoice
	 */
	public Map<String, String> getInvoiceInfo() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String start = dateFormat.format(this.startTime);
		String rentTime = Integer.toString(getTotalTime());
		int refund = depositFee - totalMoney;
		if (refund < 0) refund = 0;
		int paymoney = totalMoney - depositFee;
		if (paymoney < 0) paymoney = 0;

		
		Map<String, String> info =  new Hashtable<String, String>();
		info.put("INVOICE_ID", Integer.toString(this.invoiceId));
		info.put("START", start);
		info.put("REN_TIME", rentTime);
		info.put("BIKE_ID", String.valueOf(bike.getBikeId()));
		info.put("RENT_TYPE", "standard");
		info.put("DEPOSIT_MONEY", Integer.toString(depositFee));
		info.put("RENT_FEE", Integer.toString(totalMoney));
		info.put("REFUND", Integer.toString(refund));
		info.put("AMOUNT", Integer.toString(paymoney));

		return info;
	} 
}
