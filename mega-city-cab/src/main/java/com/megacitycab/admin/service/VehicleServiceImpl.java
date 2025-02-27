package com.megacitycab.admin.service;

import java.util.List;

import com.megacitycab.admin.dao.VehicleDAOImpl;
import com.megacitycab.model.Vehicle;

public class VehicleServiceImpl implements VehicleService{

	private VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        return vehicleDAO.addVehicle(vehicle);
    }

    @Override
    public Vehicle getVehicleById(int id) {
        return vehicleDAO.getVehicleById(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAllVehicles();
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        vehicleDAO.updateVehicle(vehicle);
    }

    @Override
    public void deleteVehicle(int id) {
        vehicleDAO.deleteVehicle(id);
    }

	@Override
	public List<Vehicle> getVehiclesWithMinSeats(int seats) {
		return vehicleDAO.getVehiclesWithMinSeats(seats);
	}
    
    
}
