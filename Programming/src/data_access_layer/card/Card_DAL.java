package data_access_layer.card;

import java.sql.*;

import data_access_layer.db.EcoBikeDB;
import javafx.scene.control.Alert;

public class Card_DAL {

	public static void payment(String cardNumber, int fee, boolean isUsed) {
	    try {
	        Connection connection = EcoBikeDB.getConnection();
	        Statement statement = connection.createStatement();
	        String query = String.format("SELECT balance FROM card WHERE cardNumber LIKE '%s'", cardNumber);
	        ResultSet resultSet = statement.executeQuery(query);
	        if (resultSet.next()) {
	            int balance = resultSet.getInt("balance");
	            int newBalance = balance - fee;
	            String updateCardQuery = String.format("UPDATE card SET balance = %d, isBeingUsed = %b WHERE cardNumber LIKE '%s'",
	                    newBalance, isUsed, cardNumber);
	            int rowsAffected = statement.executeUpdate(updateCardQuery);
	            if (rowsAffected > 0) {
	                System.out.println("Paid successfully.");
	            } else {
	                System.out.println("Failed to update card balance.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static boolean checkCard(String cardNumber, int deposit, String cardName, String exDate, String scCode) {
		boolean check = true;
		try {
			Connection connection = EcoBikeDB.getConnection();
			Statement statement = connection.createStatement();
			String query = String.format("select cardHolderName, expDate, secureCode, balance, isBeingUsed from card where cardNumber like '%s'", cardNumber);
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
                int balance = resultSet.getInt("balance");
                String expDate = resultSet.getString("expDate");
                String secureCode = resultSet.getString("secureCode");
                String cardHolder = resultSet.getString("cardHolderName");
                boolean isBeingUsed = resultSet.getBoolean("isBeingUsed");
                
                if(!cardHolder.equals(cardName)|| !expDate.equals(exDate)|| !secureCode.equals(scCode)) {
                	check = false;
                	Alert alert = new Alert(Alert.AlertType.ERROR);
        			alert.setTitle("ERROR");
        			alert.setContentText("Card details do not match!");
        			alert.showAndWait();
                }
                else if(isBeingUsed) {
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
	public static boolean checkCardReturn(String card, int fee, String cardName, String exDate, String scCode) {
		boolean check = true;
		try {
			Connection connection = EcoBikeDB.getConnection();
			Statement statement = connection.createStatement();
			String query = String.format("select cardHolderName, expDate, secureCode, balance, isBeingUsed from card where cardNumber like '%s'", card);
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				int balance = resultSet.getInt("balance");
				String expDate = resultSet.getString("expDate");
				String secureCode = resultSet.getString("secureCode");
				String cardHolder = resultSet.getString("cardHolderName");
				
				if(!cardHolder.equals(cardName)|| !expDate.equals(exDate)|| !secureCode.equals(scCode)) {
					check = false;
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setContentText("Card details do not match!");
					alert.showAndWait();
				}
				else if(balance < fee) {
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

	public static String getCardHolder(String cardNumber) {
		String cardName = null;
		try {
		Connection connection = EcoBikeDB.getConnection();
		Statement statement = connection.createStatement();
		String query = String.format("select cardHolderName from card where cardNumber like '%s'", cardNumber);
		ResultSet resultSet = statement.executeQuery(query);
		if(resultSet.next()) {
			cardName = resultSet.getString("cardHolderName");
		}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return cardName;
	}
}
