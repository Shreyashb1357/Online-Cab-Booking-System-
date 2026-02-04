package CarBooking.Final.Project.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CarBooking.Final.Project.entity.Payment;
import CarBooking.Final.Project.entity.PaymentStatus;
import CarBooking.Final.Project.entity.Ride;
import CarBooking.Final.Project.entity.User;
import CarBooking.Final.Project.repository.PaymentRepository;
import CarBooking.Final.Project.repository.RideRepository;
import CarBooking.Final.Project.repository.UserRepository;
import CarBooking.Final.Project.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private RideRepository rideRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public Payment createPayment(Integer rideId, Integer userId, Double amount, String paymentMode) {

        Ride ride = rideRepo.findById(rideId).orElse(null);
        User user = userRepo.findById(userId).orElse(null);
        if (ride == null || user == null) {
            return null;
        }
        if (!ride.getUser().getUserId().equals(userId)) {
            return null;
        }
        Payment payment = new Payment();
        payment.setRide(ride);
        payment.setUser(user);
        payment.setAmount(amount);
        payment.setPaymentMode(paymentMode);          // CASH / ONLINE
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentTime(LocalDateTime.now());

        return paymentRepo.save(payment);
    }

    @Override
    public Payment updatePaymentStatus(Integer paymentId, PaymentStatus status, String transactionId) {

        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setPaymentStatus(status);
        payment.setTransactionId(transactionId);
        payment.setPaymentTime(LocalDateTime.now());

        Ride ride = payment.getRide();
        if (PaymentStatus.PAID.equals(status)) {
            ride.setPaymentStatus(PaymentStatus.PAID);
        } else {
            ride.setPaymentStatus(PaymentStatus.FAILED);
        }
        rideRepo.save(ride);

        return paymentRepo.save(payment);
    }

    @Override
    public Payment getPaymentByRide(Integer rideId) {
        return paymentRepo.findByRide_RideId(rideId);
    }
    
    @Override
    public List<Payment> getPaymentsByUser(Integer userId) {
        return paymentRepo.findByUser_UserId(userId);
    }

    @Override
    public List<Payment> getPendingPaymentsByUser(Integer userId) {
        return paymentRepo.findByUser_UserIdAndPaymentStatus(userId, PaymentStatus.PENDING);
    }

    public Payment completePendingPayment(Integer rideId) {

        Payment payment = paymentRepo.findByRide_RideId(rideId);

        if (payment == null) {
            throw new RuntimeException("No payment found for this ride");
        }

        payment.setPaymentStatus(PaymentStatus.PAID);
        payment.setTransactionId("TXN_" + System.currentTimeMillis());
        payment.setPaymentTime(LocalDateTime.now());
        
        Ride ride = payment.getRide();
        ride.setPaymentStatus(PaymentStatus.PAID);
        rideRepo.save(ride);

        return paymentRepo.save(payment);
    }

}
