package com.megacitycab.admin.service;

import java.util.List;

import com.megacitycab.model.Driver;

public interface DriverService {
    boolean addDriver(Driver driver);
    List<Driver> getAllDrivers();
    void updateDriver(Driver driver);
    void deleteDriver(Driver driver);
    Driver getDriverById(int id);
}