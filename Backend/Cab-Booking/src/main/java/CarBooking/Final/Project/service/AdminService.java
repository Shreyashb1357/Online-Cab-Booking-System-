package CarBooking.Final.Project.service;

import java.util.List;

import CarBooking.Final.Project.entity.*;

public interface AdminService {

    List<User> getAllUsers();

    List<Driver> getAllDrivers();

    List<Ride> getAllRides();

    List<Payment> getAllPayments();
}
