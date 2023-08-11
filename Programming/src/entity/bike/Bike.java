package entity.bike;

import java.nio.channels.SelectableChannel;

public class Bike {

	private String bikeId;

	private int type;

	private String barCode;
	
	private String licensePlate;

	private boolean isBeingUsed;

	private String imgUrl;
	
	private int dockId;

	public Bike() {

	}
	

	public Bike(String bikeId, int type, String barCode, String licensePlate,boolean isBeingUsed,
			 String imgUrl, int dockId) {
		this.bikeId = bikeId;
		this.type = type;
		this.barCode = barCode;
		this.isBeingUsed = isBeingUsed;
		this.licensePlate = licensePlate;
		this.dockId = dockId;
		this.imgUrl = imgUrl;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
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