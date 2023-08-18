package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import java.util.concurrent.TimeUnit;
import data_access_layer.bike.Bike_DAL;

/**
 * @author nguyenlm Contains helper functions
 */
public class Utils {

	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Logger LOGGER = getLogger(Utils.class.getName());
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-4s] [%1$tF %1$tT] [%2$-7s] %5$s %n");
	}

	public static Logger getLogger(String className) {
		return Logger.getLogger(className);
	}

	public static String getCurrencyFormat(int num) {
		Locale vietname = new Locale("vi", "VN");
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(vietname);
		return defaultFormat.format(num);
	}

	public static String getToday() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = new Date();
	    return dateFormat.format(date);
	}

	public static String md5(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8"));
			// converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
			Utils.getLogger(Utils.class.getName());
			digest = "";
		}
		return digest;
	}

	
	public static int calculateDeposit (int type) throws SQLException {
		return Bike_DAL.getBikeValue(type)*Constant.DEPOSITE_VALUE/100;
	}
	
	public static int getDifferenceTimes(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int)TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
    }
	
	public static String minutesToTimeFormat(int minutes) {
	  int hour = minutes / 60;
	  int minute = minutes % 60;
	  return String.format("%d:%02d:00", hour, minute);
	}


	public static int getDepositeAmount(int bikeValue) {
		return bikeValue* utils.Constant.DEPOSITE_VALUE / 100;
	}

}