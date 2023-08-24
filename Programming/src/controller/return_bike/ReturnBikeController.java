package controller.return_bike;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import calculate.CalculateFee;
import calculate.ICalculator;
import common.exception.InvalidSearchKeyException;
import common.exception.NoResultException;
import controller.BaseScreenController;
import data_access_layer.bike.Bike_DAL;
import data_access_layer.dock.Dock_DAL;
import entity.bike.Bike;
import entity.dock.Dock;
import entity.invoice.Invoice;

/**
 * Controller class for return bike use case.
 *
 * @author Group4
 *
 */
public class ReturnBikeController extends BaseScreenController {
	
	//private Invoice invoice = Invoice.getRentInvoice();
	private final Invoice_DAL invoice_DAL = new Invoice_DAL();

	private final Bike_DAL bike_DAL = new Bike_DAL();

	private final Dock_DAL dock_DAL = new Dock_DAL();

	private final Card_DAL card_DAL = new Card_DAL();

	

	public int calculateFee(Invoice invoice) throws SQLException {
		Bike bike = invoice.getBike();
		ICalculator calculator = new CalculateFee();
		if(bike.type == 1){
			return calculator.calculateStandardRentFee(invoice.getTotalTime());
		}else if(bike.type == 2){
			return calculator.calculateElectricRentFee(invoice.getTotalTime());
		}else if(bike.type == 3){
			return calculator.calculateTwinRentFee(invoice.getTotalTime());
		}
	}
	public void updateTotalTime(Invoice invoice) {
		invoice.setTotalTime();
	}
	public void updateTotalMoney(int fee, Invoice invoice) {
		invoice.setTotalMoney(fee);
	}
	public void updateAfterReturnBike(int bikeId, int bikeType, int dockId) throws SQLException {
		bike_DAL.updateBikeStatus(bikeId, utils.Constant.IS_NOT_USED);
		dock_DAL.updateDockPoint(dockId);
	}

	public Invoice getInvoice(int bikeId) throws SQLException  {	
		return invoice_DAL.getRentInvoice(bikeId);
	}

	//cập nhật lại invoice trong db
	public void updateInvoice(Invoice invoice) throws SQLException {
		invoice_DAL.updateInvoice(invoice);
	}

	//ấn trả xe, người dùng nhập card để thực hiện thanh toán (hoặc nhận lại tiền)
	//check số dư của thẻ có lớn hơn fee ko
	public boolean checkCardBalance(String cardReturn, int fee, String cardName, String exDate, String scCode){
		return card_DAL.checkCardBalance(cardReturn, fee, cardName, exDate, scCode);
	}


	








//	private Dock currentDock;
//	private PaymentController payctl ;
//	private Invoice invoice;
//	
//	public Dock getCurrentDock() {
//		return currentDock;
//	}
//
//	public void setCurrentDock(Dock currentDock) {
//		this.currentDock = currentDock;
//	}
//	/**
//	 * This method get invoice information for rent bike.
//	 *
//	 * @return Map<String, String> list of result message for invoice information
//	 */
//	public Map<String, String > getInvoiceInfo() {
//			invoice = Invoice.getRentInvoice();
//			Map<String, String> result;
//			if(invoice.isRenting()) {
//				result = invoice.getInvoiceInfo();
//				result.put("STATE", "RETING");}
//			else {
//				result = new Hashtable<String, String>();
//				result.put("STATE", "NO_RETING");
//			}
//			return result;
//	}	
//	/**
//	 * This method return bike to dock.
//	 *
//	 * @param cardRent, cardHolderName, expirationDate, securityCode
//	 * @return Map<String, String> list of result message for return bike
//	 */
//	public Map<String, String> returnBike(String cardRent, String cardHolderName, String expirationDate,
//			String securityCode) throws SQLException {
//		payctl = new PaymentController();
//		Map<String, String> result = new Hashtable<String, String>();
//		result.put("RESULT", "RETURN FAILED!");
//
//		invoice = Invoice.getRentInvoice();
//		int totalfFee = invoice.getTotalMoney();
//		int deposit = invoice.getDepositFee();
//		Map<String, String> announce;
//		
//		if (totalfFee > deposit) {
//			announce = payctl.requestToPay("PAY RENT FEE" ,totalfFee - deposit, cardRent, cardHolderName, expirationDate,
//					securityCode);
//		} else {
//			announce = payctl.requestToRefund("PAY RENT FEE" ,deposit - totalfFee, cardRent, cardHolderName, expirationDate,
//					securityCode);
//		}
//
//		if (announce.get("RESULT").equals("PAYMENT SUCCESSFUL!")) {
//			currentDock.returnBikeInDock( invoice.getBike());
//			invoice.saveRentInvoice();
//			invoice.resetInvoice();
//			result.put("RESULT", "RETURN SUCCESSFUL");
//			
//		}
//		result.put("RESULT", announce.get("MESSAGE"));
//
//		return result;
//	}
}