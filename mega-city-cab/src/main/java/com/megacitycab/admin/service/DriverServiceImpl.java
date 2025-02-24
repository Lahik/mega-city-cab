package com.megacitycab.admin.service;

import com.megacitycab.admin.dao.DriverDAOImpl;
import com.megacitycab.model.Driver;
import java.util.List;

public class DriverServiceImpl implements DriverService {
    
    private DriverDAOImpl driverDAO;
    
    public DriverServiceImpl() {
    	this.driverDAO = new DriverDAOImpl();
    }
    
    @Override
    public boolean addDriver(Driver driver) {
        return driverDAO.insertDriver(driver);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverDAO.getAllDrivers();
    }

    @Override
    public void updateDriver(Driver driver) {
        driverDAO.updateDriver(driver);
    }

    @Override
    public void deleteDriver(Driver driver) {
        driverDAO.deleteDriver(driver);
    }

	@Override
	public Driver getDriverById(int id) {
		return driverDAO.getDriverById(id);
	}
}
