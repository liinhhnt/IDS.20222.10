package calculate;
import java.sql.SQLException;

import data_access_layer.bike.Bike_DAL;
import entity.bike.Bike;
import utils.Configs;

public class CalculateFee implements ICalculator{

	@Override
	public int calculateDepositFee (int type) throws SQLException {
		return Bike_DAL.getBikeValue(type)*Configs.DEPOSIT_PERCENT/100;
	}
	
	
	@Override
	public int calculateRentFee(int type , float rentTime) {
		// TODO Auto-generated method stub
		int fee = (int)rentTime*10000;
		return fee;
	}


	@Override
	public String getInfoRental(int type) {
		// Tyint typeypegenerated method stub
		return "If you return in under 10 mins, your rental is free\n"
				+ "For the first 30 mins, you are charged 10,000VND\n"
				+ "After 30 mins, retal fee will be charged 3,000VND per each 15 mins" ;
	}

}
