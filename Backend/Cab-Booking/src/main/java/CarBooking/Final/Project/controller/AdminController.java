package CarBooking.Final.Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import CarBooking.Final.Project.entity.*;
import CarBooking.Final.Project.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/drivers")
    public List<Driver> getAllDrivers() {
        return adminService.getAllDrivers();
    }

    @GetMapping("/rides")
    public List<Ride> getAllRides() {
        return adminService.getAllRides();
    }

    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return adminService.getAllPayments();
    }
}
