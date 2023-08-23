package data_access_layer.invoice;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import data_access_layer.db.EcoBikeDB;
import entity.invoice.Invoice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice_DAL {
	public void saveRentInvoice(Invoice invoice) throws SQLException {
		Statement stm = EcoBikeDB.getConnection().createStatement();
		String query = String.format(
				"update invoice set total_rent_time = '%s', total_fee = %d, status = '0' where id = %d",
				invoice.getTotalTime(), invoice.getDepositFee(), invoice.getInvoiceId());
		stm.executeUpdate(query);
	}

	public void saveNewInvoice(Invoice invoice) throws SQLException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = invoice.getStartTime().format(formatter);
	    Connection connection = EcoBikeDB.getConnection();
	    String query = String.format(
	        "INSERT INTO invoice(bikeId, startTime, totalRentTime, totalFee, deposit, status, cardNumber) " +
	        "VALUES('%s', '%s', 0, 0, %d, %d, '%s')",
	        invoice.getBike().getBikeId(), formattedDateTime, invoice.getDeposit(),
	        invoice.getStatus(), invoice.getCardNumber());

	    try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	        int rowsAffected = statement.executeUpdate();
	        if (rowsAffected > 0) {
	            ResultSet generatedKeys = statement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                invoice.setInvoiceId(generatedKeys.getInt(1));
	            }
	        }
	    }
	}
	
	public static LocalDateTime getStartTime(int bikeId) throws SQLException {
		LocalDateTime time = null;
		Connection connection = EcoBikeDB.getConnection();
        Statement statement = connection.createStatement();
		String query = String.format("SELECT startTime from invoice where bikeId = %d and status = 0", bikeId);
		try(ResultSet resultSet = statement.executeQuery(query)){
			if(resultSet.next()) {
				time = resultSet.getTimestamp("startTime").toLocalDateTime();
			}
		}
		return time;

	}
}
