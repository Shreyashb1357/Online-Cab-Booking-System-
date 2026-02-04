package CarBooking.Final.Project.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CarBooking.Final.Project.dto.DriverReportDTO;
import CarBooking.Final.Project.entity.RideStatus;
import CarBooking.Final.Project.repository.*;
import CarBooking.Final.Project.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private RideRepository rideRepo;

    @Autowired
    private DriverRepository driverRepo;

    @Override
    public Double getTotalRevenue() {
        return paymentRepo.totalRevenue();
    }

    @Override
    public Double getRevenueBetweenDates(LocalDate start, LocalDate end) {
        return paymentRepo.revenueBetweenDates(start, end);
    }

    @Override
    public List<DriverReportDTO> getTopDrivers() {
        return driverRepo.findTopDrivers();
    }

    @Override
    public Long getTotalTrips() {
        return rideRepo.count();
    }

    @Override
    public Long getCompletedTrips() {
        return rideRepo.countByStatus(RideStatus.COMPLETED);
    }
}
