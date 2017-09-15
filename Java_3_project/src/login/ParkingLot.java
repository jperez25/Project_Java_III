package login;

public class ParkingLot {
	private String hoursOfOperation;
	private String size;
	private String mapImage;
	private String nameOfLot;
	
	public ParkingLot() {}
	public ParkingLot(String hoursOfOperation, String size, String mapImage, String nameOfLot) {
		this.hoursOfOperation = hoursOfOperation;
		this.size = size;
		this.mapImage = mapImage;
		this.nameOfLot = nameOfLot;
	}

	public String getHoursOfOperation() {
		return hoursOfOperation;
	}

	public void setHoursOfOperation(String hoursOfOperation) {
		this.hoursOfOperation = hoursOfOperation;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	public String getMapImage() {
		return mapImage;
	}
	public void setMapImage(String mapImage) {
		this.mapImage = mapImage;
	}
	public String getNameOfLot() {
		return nameOfLot;
	}
	public void setNameOfLot(String nameOfLot) {
		this.nameOfLot = nameOfLot;
	}
	
}

