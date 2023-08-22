package data_access_layer.card;

import java.sql.*;

import data_access_layer.db.EcoBikeDB;
import javafx.scene.control.Alert;

public class Card_DAL {

	public static void payDeposit(String cardNumber, int deposit) {
		try {
			Connection connection = EcoBikeDB.getConnection();
			Statement statement = connection.createStatement();
			String query = String.format("select balance from card where cardNumber like '%s'", cardNumber);
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				int balance = resultSet.getInt("balance");
				int t = balance - deposit;
				String updateCardQuery = String.format("UPDATE card SET balance = '%d', isBeingUsed = '%b' WHERE cardNumber like '%s'", t, true, cardNumber);
				ResultSet set = statement.executeQuery(updateCardQuery);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static boolean checkCard(String cardNumber, int deposit) {
		boolean check = true;
		try {
			Connection connection = EcoBikeDB.getConnection();
			Statement statement = connection.createStatement();
			String query = String.format("select balance, isBeingUsed from card where cardNumber like '%s'", cardNumber);
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
                int balance = resultSet.getInt("balance");
                boolean isBeingUsed = resultSet.getBoolean("isBeingUsed");
                if(isBeingUsed) {
                	check = false;
                	Alert alert = new Alert(Alert.AlertType.WARNING);
        			alert.setTitle("WARNING");
        			alert.setContentText("This card is being used!");
        			alert.showAndWait();
                }
                else if(balance < deposit) {
                	check = false;
                	Alert alert = new Alert(Alert.AlertType.WARNING);
        			alert.setTitle("WARNING");
        			alert.setContentText("This card is not enought balance!!");
        			alert.showAndWait();
                }
			}
			else {
				check = false;
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("Card not found!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
}
