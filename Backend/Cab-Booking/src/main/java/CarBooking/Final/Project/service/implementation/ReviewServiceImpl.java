package CarBooking.Final.Project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CarBooking.Final.Project.entity.*;
import CarBooking.Final.Project.repository.*;
import CarBooking.Final.Project.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private RideRepository rideRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private DriverRepository driverRepo;

    @Override
    public Review addReview(Integer rideId, Integer rating, String comment) {

        Ride ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (ride.getStatus() != RideStatus.COMPLETED) {
            throw new RuntimeException("Ride not completed");
        }
        if (reviewRepo.existsByRide_RideId(rideId)) {
            throw new RuntimeException("Review already submitted");
        }
        Driver driver = ride.getDriver();
        Review review = new Review();
        review.setRide(ride);
        review.setDriver(driver);
        review.setUser(ride.getUser());
        review.setRating(rating);
        review.setComment(comment);
        if (driver.getTotalRatings() == null) {
            driver.setTotalRatings(0);
        }
        if (driver.getAverageRating() == null) {
            driver.setAverageRating(0.0);
        }

        int totalRatings = driver.getTotalRatings();
        double avg = driver.getAverageRating();
        double newAvg = ((avg * totalRatings) + rating) / (totalRatings + 1);
        driver.setTotalRatings(totalRatings + 1);
        driver.setAverageRating(newAvg);
        driverRepo.save(driver);
        return reviewRepo.save(review);
    }
}
