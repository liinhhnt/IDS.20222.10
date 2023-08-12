package entity.bike;

/*
 * This is class BikeRentingInfo to track the bike which is renting
 * author: Linh.pk
 */
import entity.dock.Dock;

public class BikeRentingInfo {
	private Bike bike;
	
	private int hours;
	
	private int minutes;
	
	private int seconds;
	
	private Dock returnedDock;
	
	public BikeRentingInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public BikeRentingInfo(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public Dock getReturnedDock() {
		return returnedDock;
	}

	public void setReturnedDock(Dock returnedDock) {
		this.returnedDock = returnedDock;
	}
}
