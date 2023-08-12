package entity.bike;

public class BikeType {

	private int typeId;
	
	private String name;
	
	private int noRearSeats;
	
	private int noPedals;
	
	private int noSaddles;
	
	private int value;

	public BikeType()
	{
		
	}
	
	public BikeType(int typeId, String name, int noRearSeats, int noPedals, int noSaddles, int value) {
		this.typeId = typeId;
		this.name = name;
		this.noRearSeats = noRearSeats;
		this.noPedals = noPedals;
		this.noSaddles = noSaddles;
		this.value = value;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
