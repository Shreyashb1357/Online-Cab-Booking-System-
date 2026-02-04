package CarBooking.Final.Project.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CarBooking.Final.Project.entity.*;
import CarBooking.Final.Project.repository.*;
import CarBooking.Final.Project.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DriverRepository driverRepo;

    @Autowired
    private RideRepository rideRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepo.findAll();
    }

    @Override
    public List<Ride> getAllRides() {
        return rideRepo.findAll();
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }
}
