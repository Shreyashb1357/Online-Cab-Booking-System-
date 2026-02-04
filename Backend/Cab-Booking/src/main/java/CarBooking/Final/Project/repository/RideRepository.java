package CarBooking.Final.Project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CarBooking.Final.Project.entity.*;


public interface RideRepository extends JpaRepository<Ride, Integer> {
	@EntityGraph(attributePaths = {"driver", "user"})
	 List<Ride> findByDriver_DriverId(Integer driverId);

	 @EntityGraph(attributePaths = {"driver", "user"})
	 List<Ride> findByUser_UserId(Integer userId);
	 
	 @EntityGraph(attributePaths = {"driver", "user"})
	 @Query("SELECT r FROM Ride r")
	 List<Ride> findAllWithDriverAndUser();

	 
	 Long countByStatus(RideStatus status);
	 long count();

}
