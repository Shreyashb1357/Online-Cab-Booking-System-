package CarBooking.Final.Project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CarBooking.Final.Project.dto.DriverReportDTO;
import CarBooking.Final.Project.dto.RevenueReportDTO;
import CarBooking.Final.Project.service.ReportService;

@RestController
@RequestMapping("/api/admin/reports")
public class AdminReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/revenue")
    public RevenueReportDTO totalRevenue() {
        return new RevenueReportDTO(reportService.getTotalRevenue());
    }

    @GetMapping("/revenue/range")
    public RevenueReportDTO revenueBetween(@RequestParam String start, @RequestParam String end) {
        return new RevenueReportDTO(
                reportService.getRevenueBetweenDates(LocalDate.parse(start), LocalDate.parse(end)));
    }

    @GetMapping("/drivers/top")
    public List<DriverReportDTO> topDrivers() {
        return reportService.getTopDrivers();
    }

    @GetMapping("/trips/total")
    public Long totalTrips() {
        return reportService.getTotalTrips();
    }

    @GetMapping("/trips/completed")
    public Long completedTrips() {
        return reportService.getCompletedTrips();
    }
}

