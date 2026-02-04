package CarBooking.Final.Project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CarBooking.Final.Project.entity.Driver;
import CarBooking.Final.Project.entity.Ride;
import CarBooking.Final.Project.service.RideService;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    
//    @PostMapping("/book") 
//    public Ride bookRide(@RequestParam Double pickupLat,
//                         @RequestParam Double pickupLng,
//                         @RequestParam Double dropLat,
//                         @RequestParam Double dropLng) {
//
//        return rideService.bookRide(pickupLat, pickupLng, dropLat, dropLng);
//    }
    @PostMapping("/book")
    public ResponseEntity<?> bookRide(@RequestParam Integer userId, @RequestParam Double pickupLat,@RequestParam Double pickupLng, @RequestParam Double dropLat, @RequestParam Double dropLng) {
        Ride ride = rideService.bookRide(userId,pickupLat, pickupLng, dropLat, dropLng);
        if (ride == null) {
            return ResponseEntity
                    .ok("Driver not found");
        }
        return ResponseEntity.ok(ride);
    }

    @PutMapping("/{rideId}/start")
    public Ride startRide(@PathVariable Integer rideId) {
        return rideService.startRide(rideId);
    }

    @PutMapping("/{rideId}/complete")
    public Ride completeRide(@PathVariable Integer rideId) {
        return rideService.completeRide(rideId);
    }

    
    @GetMapping("/{rideId}")
    public Ride getRideById(@PathVariable Integer rideId) {
        return rideService.getRideById(rideId);
    }

    @GetMapping("/user/{userId}")
    public List<Ride> getRidesByUser(@PathVariable Integer userId) {
        return rideService.getRidesByUser(userId);
    }

    @GetMapping("/driver/{driverId}")
    public List<Ride> getRidesByDriver(@PathVariable Integer driverId) {
        return rideService.getRidesByDriver(driverId);
    }
    
    
    @GetMapping("/{rideId}/tracking")
    public Driver getLiveDriverLocation(@PathVariable Integer rideId) {

        Ride ride = rideService.getRideById(rideId);
        if (ride == null) {
            throw new RuntimeException("Ride not found");
        }

        return ride.getDriver();
    }
    
    
    @GetMapping("/{rideId}/track")
    public Map<String, Object> trackRide(@PathVariable Integer rideId) {

        Ride ride = rideService.getRideById(rideId);
        if (ride == null) {
            throw new RuntimeException("Ride not found");
        }

        Driver driver = ride.getDriver();

        Map<String, Object> response = new HashMap<>();
        response.put("driverId", driver.getDriverId());
        response.put("latitude", driver.getLatitude()); 
        response.put("longitude", driver.getLongitude());
        response.put("rideStatus", ride.getStatus());

        return response;
    }
    
    
    @GetMapping("/{rideId}/driver-location")
    public Driver getDriverLocation(@PathVariable Integer rideId) {

        Ride ride = rideService.getRideById(rideId);

        if (ride == null) {
            throw new RuntimeException("Ride not found");
        }

        return ride.getDriver();
    }
    
    
    @PostMapping("/reject/{rideId}")
    public ResponseEntity<?> rejectRide(@PathVariable Integer rideId) {
        Ride ride = rideService.rejectRide(rideId);
        if (ride == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Ride not found");
        }
        return ResponseEntity.ok(ride);
    }




}
