package CarBooking.Final.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import CarBooking.Final.Project.entity.*;


public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
	