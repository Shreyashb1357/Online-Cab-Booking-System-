package CarBooking.Final.Project.dto;

public class RevenueReportDTO {

    private Double totalRevenue;

    public RevenueReportDTO(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }
}
