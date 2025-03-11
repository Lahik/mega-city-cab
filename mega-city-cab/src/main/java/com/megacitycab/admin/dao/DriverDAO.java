package com.megacitycab.admin.dao;

import java.util.List;

import com.megacitycab.model.Driver;

public interface DriverDAO {
    boolean insertDriver(Driver driver);
    List<Driver> getAllDrivers();
    Driver getDriverById(int id);  
    void updateDriver(Driver driver);  
    void deleteDriver(Driver driver);  
}
