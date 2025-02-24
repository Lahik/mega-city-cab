package com.megacitycab.admin.dao;

import com.megacitycab.model.Vehicle;
import java.util.List;

public interface VehicleDAO {
    boolean addVehicle(Vehicle vehicle);
    Vehicle getVehicleById(int id);
    List<Vehicle> getAllVehicles();
    void updateVehicle(Vehicle vehicle);
    void deleteVehicle(int id);
}
