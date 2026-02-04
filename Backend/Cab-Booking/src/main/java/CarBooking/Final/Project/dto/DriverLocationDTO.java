package CarBooking.Final.Project.dto;

public class DriverLocationDTO {
	private Integer rideId;
    private Integer driverId;
    private Double latitude;
    private Double longitude;
	public Integer getRideId() {
		return rideId;
	}
	public void setRideId(Integer rideId) {
		this.rideId = rideId;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
