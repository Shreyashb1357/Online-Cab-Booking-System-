package CarBooking.Final.Project.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CarBooking.Final.Project.entity.Driver;
import CarBooking.Final.Project.repository.DriverRepository;
import CarBooking.Final.Project.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepo;

    @Override
    public Driver saveDriver(Driver driver) {
        return driverRepo.save(driver);
    }

    @Override
    public Driver getDriverById(Integer driverId) {
        return driverRepo.findById(driverId).orElse(null);
    }

    @Override
    public List<Driver> getAvailableDrivers() {
        return driverRepo.findByIsAvailableTrue();
    }

    @Override
    public Driver updateAvailability(Integer driverId, Boolean isAvailable) {
        Driver driver = getDriverById(driverId);
        if (driver != null) {
            driver.setIsAvailable(isAvailable);
            return driverRepo.save(driver);
        }
        return null;
    } 

    @Override
    public Driver updateLocation(Integer driverId, Double latitude, Double longitude) {
        Driver driver = getDriverById(driverId);
        if (driver != null) {
            driver.setLatitude(latitude);
            driver.setLongitude(longitude);
            return driverRepo.save(driver);
        }
        return null;
    }

    // no jwt using
    @Override
    public Driver authenticate(String email, String password) {
        Driver driver = driverRepo.findByEmail(email);
        if (driver != null && driver.getPassword().equals(password)) {
            return driver;
        }
        return null;
    }
    
    @Override
    public boolean deleteDriverById(Integer driverId) {
        Optional<Driver> driverOpt = driverRepo.findById(driverId);
        if (driverOpt.isEmpty()) {
            return false;
        }
        driverRepo.deleteById(driverId);
        return true;
    }

}
