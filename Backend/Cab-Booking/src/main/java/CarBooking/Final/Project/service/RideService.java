package CarBooking.Final.Project.service;

import java.util.List;
import CarBooking.Final.Project.entity.Ride;

public interface RideService {

    // USER books a ride
    Ride bookRide(Integer userId,Double pickupLat, Double pickupLng, Double dropLat, Double dropLng);

    // DRIVER actions
    Ride startRide(Integer rideId);
    Ride completeRide(Integer rideId);
    Ride rejectRide(Integer rideId);

    

    // FETCH
    Ride getRideById(Integer rideId);
    List<Ride> getRidesByUser(Integer userId);
    List<Ride> getRidesByDriver(Integer driverId);
    List<Ride> getPendingRides();
    List<Ride> getRidesForDriver(Integer driverId);
}
