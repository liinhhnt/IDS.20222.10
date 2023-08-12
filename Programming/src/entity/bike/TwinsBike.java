package entity.bike;

import java.sql.Time;

public class TwinsBike extends Bike {

    public static final int BIKE_TYPE_VALUE = 3;

    public TwinsBike() {
        super();
    }

    public TwinsBike(int bikeId, int type, String barCode, String licensePlate,boolean isBeingUsed,
			 String imgUrl, int dockId, int batteryPercent, Time remainingTime) {
        super(bikeId, type, barCode, licensePlate, isBeingUsed,imgUrl, dockId);
    }

}