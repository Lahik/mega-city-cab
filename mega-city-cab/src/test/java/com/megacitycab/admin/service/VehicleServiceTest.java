package com.megacitycab.admin.service;

import com.megacitycab.model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VehicleServiceTest {

    private VehicleServiceImpl vehicleService;

    @Before
    public void setUp() {
        vehicleService = new VehicleServiceImpl();
    }

    @Test
    public void testAddVehicle() {
        Vehicle vehicle = new Vehicle("CB-1234", "SUV", "7");

        boolean result = vehicleService.addVehicle(vehicle);

        assertTrue(result);
    }

    @Test
    public void testUpdateVehicle() {
        Vehicle updatedVehicle = new Vehicle(1, "CB-5678", "Car", "4");

        vehicleService.updateVehicle(updatedVehicle);

        assertEquals("CB-5678", vehicleService.getVehicleById(1).getVehicleNumber());
        assertEquals("Car", vehicleService.getVehicleById(1).getVehicleType());
        assertEquals("4", vehicleService.getVehicleById(1).getNoOfSeats());
    }

    @Test
    public void testDeleteVehicle() {
        Vehicle vehicle = new Vehicle(1, "CB-5678", "Van", "10");

        vehicleService.deleteVehicle(vehicle.getId());

        assertNull(vehicleService.getVehicleById(1));
    }
}
