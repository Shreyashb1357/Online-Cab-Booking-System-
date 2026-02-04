package CarBooking.Final.Project.service;

import CarBooking.Final.Project.entity.User;

public interface UserService {

    User registerUser(User user);

    User authenticate(String email, String password);

    User getUserById(Integer userId);
    
    boolean deleteUserById(Integer userId);

}
