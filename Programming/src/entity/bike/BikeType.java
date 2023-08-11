package entity.bike;

public class BikeType {

	private int typeId;
	
	private String name;
	
	private int noRearSeats;
	
	private int noPedals;
	
	private int noSaddles;
	
	private float value;

	public BikeType() {
		// TODO Auto-generated constructor stub
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoRearSeats() {
		return noRearSeats;
	}

	public void setNoRearSeats(int noRearSeats) {
		this.noRearSeats = noRearSeats;
	}

	public int getNoPedals() {
		return noPedals;
	}

	public void setNoPedals(int noPedals) {
		this.noPedals = noPedals;
	}

	public int getNoSaddles() {
		return noSaddles;
	}

	public void setNoSaddles(int noSaddles) {
		this.noSaddles = noSaddles;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

}
