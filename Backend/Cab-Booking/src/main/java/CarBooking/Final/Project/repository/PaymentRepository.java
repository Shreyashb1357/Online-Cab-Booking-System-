package CarBooking.Final.Project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CarBooking.Final.Project.entity.Payment;
import CarBooking.Final.Project.entity.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findByRide_RideId(Integer rideId);
    
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.paymentStatus = CarBooking.Final.Project.entity.PaymentStatus.PAID")
    Double totalRevenue();

    @Query("""
    SELECT SUM(p.amount)
    FROM Payment p
    WHERE p.paymentStatus = CarBooking.Final.Project.entity.PaymentStatus.PAID
    AND DATE(p.paymentTime) BETWEEN :start AND :end
    """)
    Double revenueBetweenDates(LocalDate start, LocalDate end);
    
    List<Payment> findByUser_UserId(Integer userId);

    List<Payment> findByUser_UserIdAndPaymentStatus(Integer userId, PaymentStatus paymentStatus);

}









//package CarBooking.Final.Project.repository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import CarBooking.Final.Project.entity.Payment;
//
//public interface PaymentRepository extends JpaRepository<Payment, Integer> {
//
//    Payment findByRide_RideId(Integer rideId);
//    
//    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.paymentStatus = 'SUCCESS'")
//    Double totalRevenue();
//
//    @Query("""
//    SELECT SUM(p.amount)
//    FROM Payment p
//    WHERE p.paymentStatus = 'SUCCESS'
//    AND DATE(p.paymentTime) BETWEEN :start AND :end
//    """)
//    Double revenueBetweenDates(LocalDate start, LocalDate end);
//    
//    List<Payment> findByUser_UserId(Integer userId);
//
//    List<Payment> findByUser_UserIdAndPaymentStatus(Integer userId, String paymentStatus);
//
//}
