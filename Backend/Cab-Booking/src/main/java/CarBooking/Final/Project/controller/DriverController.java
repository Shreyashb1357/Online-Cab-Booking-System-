package CarBooking.Final.Project.controller; 

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CarBooking.Final.Project.entity.Driver;
import CarBooking.Final.Project.entity.Ride;
import CarBooking.Final.Project.service.DriverService;
import CarBooking.Final.Project.service.RideService;
import CarBooking.Final.Project.service.UserService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;
    
    @Autowired
    private RideService rideService;

  
    @PostMapping("/register")
    public Driver registerDriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }

    @PostMapping("/login")
    public Driver login(@RequestParam String email, @RequestParam String password) {
        return driverService.authenticate(email, password);
    }

    @GetMapping("/{driverId}")
    public Driver getDriverById(@PathVariable Integer driverId) {
        return driverService.getDriverById(driverId);
    }

    @GetMapping("/available")
    public List<Driver> getAvailableDrivers() {
        return driverService.getAvailableDrivers();
    }

    @PutMapping("/{driverId}/availability")
    public Driver updateAvailability(@PathVariable Integer driverId, @RequestParam Boolean isAvailable) {
        return driverService.updateAvailability(driverId, isAvailable);
    }
    
    @PutMapping("/{driverId}/location")
    public Driver updateLocation(@PathVariable Integer driverId, @RequestParam Double latitude, @RequestParam Double longitude) {
        return driverService.updateLocation(driverId, latitude, longitude);
    }
    
    @GetMapping("/rides/pending")
    public List<Ride> pendingRides() {
        return rideService.getPendingRides();
    }


    @GetMapping("/{driverId}/rides")
    public List<Ride> getDriverRides(@PathVariable Integer driverId) {
        return rideService.getRidesForDriver(driverId);
    }
    
    @DeleteMapping("/{driverId}")
    public ResponseEntity<?> deleteDriver(@PathVariable Integer driverId) {
        boolean deleted = driverService.deleteDriverById(driverId);
        if (!deleted) {
            return ResponseEntity.ok(
                    Map.of("message", "Driver not found")
            );
        }
        return ResponseEntity.ok(
                Map.of("message", "Driver deleted successfully")
        );
    }


  
    
}
