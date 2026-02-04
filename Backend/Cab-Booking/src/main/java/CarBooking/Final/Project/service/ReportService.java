package CarBooking.Final.Project.service;

import java.time.LocalDate;
import java.util.List;
import CarBooking.Final.Project.dto.*;

public interface ReportService {

    Double getTotalRevenue();

    Double getRevenueBetweenDates(LocalDate start, LocalDate end);

    List<DriverReportDTO> getTopDrivers();

    Long getTotalTrips();

    Long getCompletedTrips();
}
