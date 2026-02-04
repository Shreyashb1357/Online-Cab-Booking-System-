package CarBooking.Final.Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import CarBooking.Final.Project.entity.Payment;
import CarBooking.Final.Project.entity.PaymentStatus;
import CarBooking.Final.Project.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public Payment createPayment(@RequestParam Integer rideId, @RequestParam Integer userId, @RequestParam Double amount, @RequestParam String paymentMode) {
        return paymentService.createPayment(rideId,userId,amount, paymentMode);
    }

    @PutMapping("/{paymentId}/status")
    public Payment updatePaymentStatus( @PathVariable Integer paymentId, @RequestParam PaymentStatus status,@RequestParam(required = false) String transactionId) {
        return paymentService.updatePaymentStatus(paymentId,status,transactionId);
    }

    @GetMapping("/ride/{rideId}")
    public Payment getPaymentByRide(@PathVariable Integer rideId) {
        return paymentService.getPaymentByRide(rideId);
    }
    
    @GetMapping("/user/{userId}") 
    public List<Payment> getPaymentsByUser(@PathVariable Integer userId) {
        return paymentService.getPaymentsByUser(userId);
    }

    @GetMapping("/user/{userId}/pending")
    public List<Payment> getPendingPayments(@PathVariable Integer userId) {
        return paymentService.getPendingPaymentsByUser(userId);
    }

    @PutMapping("/ride/{rideId}/pay")
    public Payment payForRide(@PathVariable Integer rideId) {
        return paymentService.completePendingPayment(rideId);
    }

}
