package com.megacitycab.admin.service;

import java.util.List;

import com.megacitycab.model.Vehicle;

public interface VehicleService {
    boolean addVehicle(Vehicle vehicle);
    Vehicle getVehicleById(int id);
    List<Vehicle> getAllVehicles();
    void updateVehicle(Vehicle vehicle);
    void deleteVehicle(int id);
    List<Vehicle> getVehiclesWithMinSeats(int seats);
}
