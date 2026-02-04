package CarBooking.Final.Project.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CarBooking.Final.Project.entity.User;
import CarBooking.Final.Project.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        return userService.authenticate(email, password);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        boolean deleted = userService.deleteUserById(userId);
        if (!deleted) {
            return ResponseEntity.ok(
                    Map.of("message", "User not found")
            );
        }
        return ResponseEntity.ok(
                Map.of("message", "User deleted successfully")
        );
    }
}
