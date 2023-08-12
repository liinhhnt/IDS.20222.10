package entity.bike;

import java.sql.Time;

public class StandardBike extends Bike {

    public static final int BIKE_TYPE_VALUE = 1;

    public StandardBike() {
        super();
    }
    
    public StandardBike(int bikeId, int type, String barCode, String licensePlate,boolean isBeingUsed,
			 String imgUrl, int dockId, int batteryPercent, Time remainingTime) {
        super(bikeId, type, barCode, licensePlate, isBeingUsed,imgUrl, dockId);
    }

}