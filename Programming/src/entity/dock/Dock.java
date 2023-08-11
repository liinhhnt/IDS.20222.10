package entity.dock;

/**
 * Represent dock entity.
 *
 * @author ntlinh
 *
 */
public class Dock {
	  private int dockId;
	  private String name;
	  private String address;
	  private int noOfEmptyPoints;
	  private int noOfBikes;
	  private int area;
	  private String imageUrl;	  
	  
	  public Dock() {
		  
	  }
	  
//	  public Dock(int dockId, String name, String address, int area, int noOfEmptyPoints, String imgUrl) {
//		    this.dockId = dockId;
//		    this.address = address;
//		    this.name = name;
//		    this.area = area;
//		    this.noOfEmptyPoints = noOfEmptyPoints;
//		    this.imageUrl = imgUrl;
//		  }
	  
	  public Dock(int dockId, String name, String address, int noOfEmptyPoints, int noOfBikes, int area, String imgUrl) {
	    this.dockId = dockId;
	    this.address = address;
	    this.name = name;
	    this.noOfEmptyPoints = noOfEmptyPoints;
	    this.noOfBikes = noOfBikes;
	    this.area = area;
	    this.imageUrl = imgUrl;
	  }

	public int getDockId() {
		return dockId;
	}

	public void setDockId(int dockId) {
		this.dockId = dockId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfEmptyPoints() {
		return noOfEmptyPoints;
	}

	public void setNoOfEmptyPoints(int noOfEmptyPoints) {
		this.noOfEmptyPoints = noOfEmptyPoints;
	}

	public int getNoOfBikes() {
		return noOfBikes;
	}

	public void setNoOfBikes(int noOfBikes) {
		this.noOfBikes = noOfBikes;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	  
}