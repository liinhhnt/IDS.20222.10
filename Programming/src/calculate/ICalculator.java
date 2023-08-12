package calculate;
import java.sql.SQLException;

import entity.bike.Bike;
/*
 * An interface for calculating fee including reting fee and deposit
 * other fee calculato will implement this interface
 */
public interface ICalculator {
	/*
     * This method calculate deposit.
     * @param bike biketype to determine the deposit.
     * @return the deposit amount.
     */
	public int calculateDepositFee(int value) throws SQLException;
	
	/*
	 * This method calculate rent fee.
	 * type: the biketype of the rent bike.
	 * rentTime: total rent time.
	 * return: the rental fee.
	 */
	public int calculateRentFee(int type , float rentTime);
	/*
	 * This method returns details about the rental method.
	 * @type: the biketype.
	 * return: String the rental method represented in a string.
	 */
	public String getInfoRental(int type);


}
