package CarBooking.Final.Project.dto;

public class DriverReportDTO {

    private Integer driverId;
    private String name;
    private Double averageRating;
    private Long totalRides;

    public DriverReportDTO(Integer driverId, String name, Double averageRating, Long totalRides) {
        this.driverId = driverId;
        this.name = name;
        this.averageRating = averageRating;
        this.totalRides = totalRides;
    }

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public Long getTotalRides() {
		return totalRides;
	}

	public void setTotalRides(Long totalRides) {
		this.totalRides = totalRides;
	}

    // getters
}
