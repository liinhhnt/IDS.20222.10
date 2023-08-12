package entity.bike;

import java.sql.Time;

public class StandardEBike extends Bike {

    public static final int BIKE_TYPE_VALUE = 2;

    private int batteryPercent;
    
    private Time remainingTime;

    public StandardEBike() {
        super();
    }
    
    public StandardEBike(String bikeId, int type, String barCode, String licensePlate, float value, boolean isBeingUsed,
			int rearSeats, int pedals, int saddles, String imgUrl, int batteryPercent, Time remainingTime) {
        super(bikeId, type, barCode, licensePlate, value, isBeingUsed, rearSeats, pedals, saddles, imgUrl);
        this.batteryPercent = batteryPercent;
        this.remainingTime = remainingTime;
    }

    // getter setter operation
    public int getBatteryPercent() {
        return batteryPercent;
    }

    public void setBatteryPercent(int batteryPercent) {
        this.batteryPercent = batteryPercent;
    }

	public Time getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(Time remainingTime) {
		this.remainingTime = remainingTime;
	}
}