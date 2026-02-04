package CarBooking.Final.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import CarBooking.Final.Project.dto.DriverLocationDTO;
import CarBooking.Final.Project.entity.Driver;
import CarBooking.Final.Project.repository.DriverRepository;

@Controller
public class DriverTrackingController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private DriverRepository driverRepo;

    @MessageMapping("/driver/location")
    public void updateDriverLocation(DriverLocationDTO location) {
        Driver driver = driverRepo.findById(location.getDriverId()).orElse(null);
        if (driver != null) {
            driver.setLatitude(location.getLatitude());
            driver.setLongitude(location.getLongitude());
            driverRepo.save(driver);
        }
        messagingTemplate.convertAndSend("/topic/ride/" + location.getRideId(), location);
    }
}
