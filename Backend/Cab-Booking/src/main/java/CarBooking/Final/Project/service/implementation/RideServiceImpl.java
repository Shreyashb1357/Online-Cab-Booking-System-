package CarBooking.Final.Project.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CarBooking.Final.Project.config.FareConfig;
import CarBooking.Final.Project.entity.Driver;
import CarBooking.Final.Project.entity.Payment;
import CarBooking.Final.Project.entity.PaymentStatus;
import CarBooking.Final.Project.entity.Ride;
import CarBooking.Final.Project.entity.RideStatus;
import CarBooking.Final.Project.entity.User;
import CarBooking.Final.Project.repository.DriverRepository;
import CarBooking.Final.Project.repository.PaymentRepository;
import CarBooking.Final.Project.repository.RideRepository;
import CarBooking.Final.Project.repository.UserRepository;
import CarBooking.Final.Project.service.GoogleMapsService;
import CarBooking.Final.Project.service.RideService;
import CarBooking.Final.Project.util.DistanceUtil;

@Service
@Transactional
public class RideServiceImpl implements RideService {

    @Autowired
    private RideRepository rideRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DriverRepository driverRepo;
    
    @Autowired
    private FareConfig fareConfig;
    
    @Autowired
    private GoogleMapsService googleMapsService;
    
    @Autowired
    private PaymentRepository paymentRepo;

    private static final double BASE_FARE = 50.0;
    private static final double RATE_PER_KM = 10.0;

    @Override
    public Ride bookRide(Integer userId, Double pickupLat, Double pickupLng, Double dropLat, Double dropLng) {
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty()) {
            return null; 
        }

        User user = userOpt.get();

        List<Driver> drivers = driverRepo.findByIsAvailableTrue();
        if (drivers == null || drivers.isEmpty()) {
            return null;
        }

        Driver driver = drivers.get(0);
        driver.setIsAvailable(false);
        driverRepo.save(driver);

        double distance = calculateDistance( pickupLat, pickupLng, dropLat, dropLng);

        double fare = BASE_FARE + distance * RATE_PER_KM;

        Ride ride = new Ride();
        ride.setUser(user);           
        ride.setDriver(driver);
        ride.setPickupLat(pickupLat);
        ride.setPickupLng(pickupLng);
        ride.setDropLat(dropLat);
        ride.setDropLng(dropLng);
        ride.setDistance(distance);
        ride.setFare(fare);
        ride.setStatus(RideStatus.REQUESTED);
        ride.setPaymentStatus(PaymentStatus.PENDING);

        return rideRepo.save(ride);
    }

    @Override
    public Ride startRide(Integer rideId) {
        Ride ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        if (ride.getStatus() != RideStatus.REQUESTED) {
            throw new RuntimeException("Ride cannot be started");
        }
        ride.setStatus(RideStatus.STARTED);
        return rideRepo.save(ride);
    }

    @Override
    public Ride completeRide(Integer rideId) {
        Ride ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (ride.getStatus() != RideStatus.STARTED) {
            throw new RuntimeException("Ride cannot be completed");
        }

        ride.setStatus(RideStatus.COMPLETED);
        ride.setPaymentStatus(PaymentStatus.PENDING);

        // Free driver
        Driver driver = ride.getDriver();
        driver.setIsAvailable(true);
        driverRepo.save(driver);
        
        Payment payment = new Payment();
        payment.setRide(ride);
        payment.setUser(ride.getUser());
        payment.setAmount(ride.getFare());
        payment.setPaymentMode("NOT_SELECTED");
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setTransactionId(null);

        paymentRepo.save(payment);


        return rideRepo.save(ride);
    }

    //fetch
    @Override
    public Ride getRideById(Integer rideId) {
        return rideRepo.findById(rideId).orElse(null);
    }

    @Override
    public List<Ride> getRidesByUser(Integer userId) {
        return rideRepo.findByUser_UserId(userId);
    }

    @Override
    public List<Ride> getRidesByDriver(Integer driverId) {
        return rideRepo.findByDriver_DriverId(driverId);
    }
    
    @Override
    public List<Ride> getPendingRides() {
        return rideRepo.findAllWithDriverAndUser()
                .stream()
                .filter(ride -> ride.getStatus() == RideStatus.REQUESTED)
                .toList();
    }

    
    @Override
    public List<Ride> getRidesForDriver(Integer driverId) {
        return rideRepo.findByDriver_DriverId(driverId);
    }
    
    
    @Override
    public Ride rejectRide(Integer rideId) {
        Ride ride = rideRepo.findById(rideId).orElse(null);
        if (ride == null) {
            return null;
        }
        if (ride.getStatus() != RideStatus.REQUESTED) {
            return ride;
        }
        ride.setStatus(RideStatus.CANCELLED);
        Driver driver = ride.getDriver();
        if (driver != null) {
            driver.setIsAvailable(true);
            driverRepo.save(driver);
        }
        return rideRepo.save(ride);
    }


   //  UTIL method
    private double calculateDistance(double lat1, double lng1,double lat2, double lng2) {
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lng2 - lng1, 2)) * 111;
    }
}
