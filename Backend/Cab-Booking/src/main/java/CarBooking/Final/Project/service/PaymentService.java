package CarBooking.Final.Project.service;

import java.util.List;

import CarBooking.Final.Project.entity.Payment;
import CarBooking.Final.Project.entity.PaymentStatus;

public interface PaymentService {

    Payment createPayment(Integer rideId, Integer userId, Double amount, String paymentMode);

    Payment updatePaymentStatus(Integer paymentId, PaymentStatus status, String transactionId);

    Payment getPaymentByRide(Integer rideId);
    List<Payment> getPendingPaymentsByUser(Integer userId);
    List<Payment> getPaymentsByUser(Integer userId);
    Payment completePendingPayment(Integer rideId);
}
