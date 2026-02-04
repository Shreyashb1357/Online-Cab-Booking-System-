package CarBooking.Final.Project.repository;

import java.util.List;

import CarBooking.Final.Project.dto.DriverReportDTO;
import CarBooking.Final.Project.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DriverRepository extends JpaRepository<Driver, Integer> {
	List<Driver> findByIsAvailableTrue();
	Driver findByEmail(String email);

	@Query("""
	SELECT new CarBooking.Final.Project.dto.DriverReportDTO(
	    d.driverId,
	    d.name,
	    d.averageRating,
	    COUNT(r)
	)
	FROM Driver d
	JOIN Ride r ON r.driver = d
	WHERE r.status = 'COMPLETED'
	GROUP BY d.driverId
	ORDER BY d.averageRating DESC
	""")
	List<DriverReportDTO> findTopDrivers();

	
}
