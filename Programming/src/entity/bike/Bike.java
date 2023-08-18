package entity.bike;

import java.nio.channels.SelectableChannel;

import factory.*;

public class Bike {

	private int bikeId;

	private int type;

	private String barCode;
	
	private String licensePlate;

	private boolean isBeingUsed;

	private String imgUrl;
	
	private int dockId;

	public Bike() {

	}
	
	public static Bike createBike(int bikeType) {
		BikeGenerator bikeGenerate = new BikeGenerator();

		switch (bikeType) {
			case StandardBike.BIKE_TYPE_VALUE:
				bikeGenerate.setTypeBikeFactory(new StandardBikeFactory());
				return bikeGenerate.createBike();
			case StandardEBike.BIKE_TYPE_VALUE:
				bikeGenerate.setTypeBikeFactory(new StandardEBikeFactory());
				return bikeGenerate.createBike();
			case TwinsBike.BIKE_TYPE_VALUE:
				bikeGenerate.setTypeBikeFactory(new TwinsBikeFactory());
				return bikeGenerate.createBike();
			default:
				return null;
		}
	}

	public Bike(int bikeId, int type, String barCode, String licensePlate,boolean isBeingUsed,
			 String imgUrl, int dockId) {
		this.bikeId = bikeId;
		this.type = type;
		this.barCode = barCode;
		this.isBeingUsed = isBeingUsed;
		this.licensePlate = licensePlate;
		this.dockId = dockId;
		this.imgUrl = imgUrl;
	}

	public int getBikeId() {
		return bikeId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public boolean getIsBeingUsed() {
		return isBeingUsed;
	}

	public void setBeingUsed(boolean isBeingUsed) {
		this.isBeingUsed = isBeingUsed;
	}

	public int getDockId() {
		return dockId;
	}

	public void setDockId(int dockId) {
		this.dockId = dockId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


}