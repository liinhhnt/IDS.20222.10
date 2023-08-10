package entity.bike;

public class Bike {

	private String bikeId;

	private int type;

	private String barCode;
	
	private String licensePlate;

	private float value;

	private boolean isBeingUsed;

	private int rearSeats;
	
	private int pedals;
	
	private int saddles;

	private String imgUrl;

	public Bike() {

	}

	public Bike(String bikeId, int type, String barCode, String licensePlate, float value, boolean isBeingUsed,
			int rearSeats, int pedals, int saddles, String imgUrl) {
		this.bikeId = bikeId;
		this.type = type;
		this.barCode = barCode;
		this.value = value;
		this.isBeingUsed = isBeingUsed;
		this.licensePlate = licensePlate;
		this.rearSeats = rearSeats;
		this.pedals = pedals;
		this.saddles = saddles;
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

	public float getValue() {
		return this.value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public boolean isBeingUsed() {
		return isBeingUsed;
	}

	public void setBeingUsed(boolean isBeingUsed) {
		this.isBeingUsed = isBeingUsed;
	}

	public int getRearSeats() {
		return rearSeats;
	}

	public void setRearSeats(int rearSeats) {
		this.rearSeats = rearSeats;
	}

	public int getPedals() {
		return pedals;
	}

	public void setPedals(int pedals) {
		this.pedals = pedals;
	}

	public int getSaddles() {
		return saddles;
	}

	public void setSaddles(int saddles) {
		this.saddles = saddles;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}