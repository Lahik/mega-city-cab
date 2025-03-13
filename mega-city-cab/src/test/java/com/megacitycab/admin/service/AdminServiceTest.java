package com.megacitycab.admin.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.megacitycab.model.Admin;

public class AdminServiceTest {

    private AdminServiceImpl adminService;

    @Before
    public void setUp() {
        adminService = new AdminServiceImpl(); 
    }

    @Test
    public void testAuthenticateAdmin_InvalidCredentials() {
        boolean result = adminService.authenticateUser("abcd", "1234");
        assertFalse(result);  
    }
    
    @Test
    public void testAdminUser() {
        Admin admin = new Admin(1, "admin", "admin");
        boolean isCreated = adminService.createUser(admin);
        assertTrue(isCreated); 
    }

    @Test
    public void testLoginAdmin() {
        boolean result = adminService.authenticateUser("admin", "admin");
        assertTrue(result);  
    }

    @Test
    public void testGetAdminDetails_UserNotFound() {
        Admin admin = adminService.getUserDetails("nonexistentUser");
        assertNull(admin); 
    }

    @Test
    public void testIsUsernameTaken_NoUser() {
        boolean isTaken = adminService.isUsernameTaken("admin", 0);
        assertTrue(isTaken); 
    }
}
