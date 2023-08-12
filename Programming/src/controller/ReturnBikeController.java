package controller;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import calculate.CalculateFee;
import calculate.ICalculator;
import common.exception.InvalidSearchKeyException;
import common.exception.NoResultException;
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
	private Dock currentDock;
	//private PaymentController payctl ;
	private Invoice invoice;
	
	public Dock getCurrentDock() {
		return currentDock;
	}

	public void setCurrentDock(Dock currentDock) {
		this.currentDock = currentDock;
	}
	/**
	 * This method get invoice information for rent bike.
	 *
	 * @return Map<String, String> list of result message for invoice information
	 */
	public Map<String, String > getInvoiceInfo() {
			invoice = Invoice.getRentInvoice();
			Map<String, String> result;
			if(invoice.isRenting()) {
				result = invoice.getInvoiceInfo();
				result.put("STATE", "RETING");}
			else {
				result = new Hashtable<String, String>();
				result.put("STATE", "NO_RETING");
			}
			return result;
	}	
	/**
	 * This method return bike to dock.
	 *
	 * @param cardNumber, cardHolderName, expirationDate, securityCode
	 * @return Map<String, String> list of result message for return bike
	 */
//	public Map<String, String> returnBike(String cardNumber, String cardHolderName, String expirationDate,
//			String securityCode) throws SQLException {
//		//payctl = new PaymentController();
//		Map<String, String> result = new Hashtable<String, String>();
//		result.put("RESULT", "RETURN FAILED!");
//
//		invoice = Invoice.getRentInvoice();
//		int totalfFee = invoice.getTotalMoney();
//		int deposit = invoice.getDepositFee();
//		Map<String, String> announce;
//		
//		if (totalfFee > deposit) {
//			announce = payctl.requestToPay("PAY RENT FEE" ,totalfFee - deposit, cardNumber, cardHolderName, expirationDate,
//					securityCode);
//		} else {
//			announce = payctl.requestToRefund("PAY RENT FEE" ,deposit - totalfFee, cardNumber, cardHolderName, expirationDate,
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