package com.megacitycab.admin.service;

import com.megacitycab.model.Driver;
import java.util.List;

public interface DriverService {
    boolean addDriver(Driver driver);
    List<Driver> getAllDrivers();
    void updateDriver(Driver driver);
    void deleteDriver(Driver driver);
    Driver getDriverById(int id);
}