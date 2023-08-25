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
import data_access_layer.card.Card_DAL;
import data_access_layer.dock.Dock_DAL;
import data_access_layer.invoice.Invoice_DAL;
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

	
	//lấy ra invoice của cái xe định return
	public Invoice getInvoice(Bike bike) throws SQLException  {	
		return invoice_DAL.getRentInvoice(bike);
	}
//tính phí thuê xe
	public int calculateFee(Invoice invoice) throws SQLException {
		Bike bike = invoice.getBike();
		ICalculator calculator = new CalculateFee();
		if(bike.getType() == 1){
			return calculator.calculateStandardRentFee(invoice.getTotalTime());
		}else if(bike.getType() == 2){
			return calculator.calculateElectricRentFee(invoice.getTotalTime());
		}else {
			return calculator.calculateTwinRentFee(invoice.getTotalTime());
		}
	}

	public void updateTotalTime(Invoice invoice) {
		invoice.setTotalTime();
	}
	public void updateTotalMoney(int fee, Invoice invoice) {
		invoice.setTotalMoney(fee);
	}

	//update lại tình trạng xe, và tình trạng của dock
	public void updateAfterReturnBike(int bikeId, int bikeType, int dockId) throws SQLException {
		bike_DAL.updateBikeStatus(bikeId, utils.Constant.IS_NOT_USED);
		dock_DAL.updateDockPoint(dockId);
	}

	

	//cập nhật lại invoice trong db
	public void updateInvoice(Invoice invoice) throws SQLException {
		invoice_DAL.updateInvoice(invoice);
	}

	//ấn trả xe, người dùng nhập card để thực hiện thanh toán (hoặc nhận lại tiền)
	//check số dư của thẻ có lớn hơn fee ko
	public boolean checkCardBalance(String cardReturn, int fee, String cardName, String exDate, String scCode){
		return card_DAL.checkCardReturn(cardReturn, fee, cardName, exDate, scCode);
	}
}