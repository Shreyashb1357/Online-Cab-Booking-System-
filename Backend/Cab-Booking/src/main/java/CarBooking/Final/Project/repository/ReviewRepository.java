package CarBooking.Final.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import CarBooking.Final.Project.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    boolean existsByRide_RideId(Integer rideId);
}
