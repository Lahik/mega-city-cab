package com.megacitycab.admin.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.megacitycab.model.Driver;

public class DriverServiceTest {

    private DriverServiceImpl driverService;

    @Before
    public void setUp() {
        driverService = new DriverServiceImpl();
    }

    @Test
    public void testAddDriver() {
        Driver driver = new Driver("Kumara", "1234");

        boolean result = driverService.addDriver(driver);

        assertTrue(result);
    }

    @Test
    public void testUpdateDriver() {
        Driver updatedDriver = new Driver(6, "Kumara Updated", "1234");

        driverService.updateDriver(updatedDriver);

        assertEquals("Kumara Updated", driverService.getDriverById(6).getName());
    }
    
    @Test
    public void testDeleteDriver() {
        Driver driver = new Driver(6, "Kumara Updated", "1234");

        driverService.deleteDriver(driver);

        assertNull(driverService.getDriverById(6));
    }
}
