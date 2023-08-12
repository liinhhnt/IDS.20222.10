package data_access_layer.invoice;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import data_access_layer.db.EcoBikeDB;
import entity.invoice.Invoice;

public class Invoice_DAL {
	public void saveRentInvoice(Invoice invoice) throws SQLException {
		  Statement stm = EcoBikeDB.getConnection().createStatement();
		  String query = String.format("update invoices set total_rent_time = '%s', total_fee = %d, status = '0' where id = %d", invoice.getTotalTime(), invoice.getDepositFee(), invoice.getInvoiceId());
		  stm.executeUpdate(query);
		}
	
	
	public void saveNewInvoice(Invoice invoice) throws SQLException {
		  Statement stm = EcoBikeDB.getConnection().createStatement();
		  String query = String.format("insert into invoices(bike_id, start_time, total_rent_time, total_fee, deposit, status)"
		                             + "values(%d, '%s', 0, 0, %d, '%d')"
		                             + "returning id;", 
		                             invoice.getBike().getBikeId(), invoice.getStartTime().toString(), invoice.getDepositFee(), invoice.getStatus());
		  ResultSet res = stm.executeQuery(query);
		  if(res.next()) {
		    invoice.setInvoiceId(res.getInt("id"));
		  }
	}
}
