package calculate;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import data_access_layer.bike.Bike_DAL;
import entity.bike.Bike;
import utils.Configs;
import utils.Constant;

public class CalculateFee implements ICalculator{

	@Override
	public int calculateDepositFee (int value) throws SQLException {
		return value*Constant.DEPOSIT_VALUE/100;
	}
	
	
	@Override
	public int calculateStandardRentFee( float rentTime) {
		// TODO Auto-generated method stub
		if(rentTime < 10) {
			return 0;
		}else if(rentTime < 30) {
			return 10000;
		} else {
			return 10000 + (int)Math.ceil((rentTime - 30)/15) * 3000;
		}
	}

	@Override
	public int calculateElectricRentFee(float rentTime){
		return (int)1.5 * calculateStandardRentFee(rentTime);
	}

	@Override
	public int calculateTwinRentFee(float rentTime){
		return (int)1.5 * calculateStandardRentFee(rentTime);
	}

	@Override
	public String getInfoRental(int type) {
		// Tyint typeypegenerated method stub
		return "If you return in under 10 mins, your rental is free\n"
				+ "For the first 30 mins, you are charged 10,000VND\n"
				+ "After 30 mins, retal fee will be charged 3,000VND per each 15 mins" ;
	}
	@Override
	public int getTotalTime(LocalDateTime start, LocalDateTime now) {
		return (int) ChronoUnit.MINUTES.between(start, now);
	}
}
