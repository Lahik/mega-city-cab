package com.megacitycab.admin.dao;

import com.megacitycab.model.Driver;
import java.util.List;

public interface DriverDAO {
    boolean insertDriver(Driver driver);
    List<Driver> getAllDrivers();
    Driver getDriverById(int id);  
    void updateDriver(Driver driver);  
    void deleteDriver(Driver driver);  
}
