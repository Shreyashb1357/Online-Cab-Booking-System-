package CarBooking.Final.Project.service;

import java.util.List;
import CarBooking.Final.Project.entity.Driver;

public interface DriverService {

    Driver saveDriver(Driver driver);

    Driver getDriverById(Integer driverId);

    List<Driver> getAvailableDrivers();

    Driver updateAvailability(Integer driverId, Boolean isAvailable);

    Driver updateLocation(Integer driverId, Double latitude, Double longitude);

    Driver authenticate(String email, String password);
    boolean deleteDriverById(Integer driverId);

}
