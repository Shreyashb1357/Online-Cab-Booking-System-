package CarBooking.Final.Project.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CarBooking.Final.Project.entity.User;
import CarBooking.Final.Project.repository.UserRepository;
import CarBooking.Final.Project.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public User registerUser(User user) {

        if (user == null || user.getEmail() == null) {
            return null;
        }

        User existing = userRepo.findByEmail(user.getEmail());
        if (existing != null) {
            return null;
        }

        return userRepo.save(user);
    }

    @Override
    public User authenticate(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepo.findById(userId).orElse(null);
    }
    
    @Override
    public boolean deleteUserById(Integer userId) {
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty()) {
            return false;
        }
        userRepo.deleteById(userId);
        return true;
    }
}
