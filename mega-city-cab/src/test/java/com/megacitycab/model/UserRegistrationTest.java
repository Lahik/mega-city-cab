package com.megacitycab.model;

import static org.junit.Assert.*;
import org.junit.Test;
import com.megacitycab.database.DBConnectionFactory;
import org.junit.After;
import org.junit.Before;
import java.sql.*;

public class UserRegistrationTest {
    
    private Connection conn;

    @Before
    public void setUp() throws SQLException {
    	conn = DBConnectionFactory.getConnection();
    	conn.setAutoCommit(false);	
    }

    @Test
    public void testRegisterSuccessful() throws SQLException {
        User user = new User("Perera", "123 Cmb St", "123456789V", "0777123456", "perera", "perera123");

        boolean isRegistered = registerUser(user);

        assertTrue(isRegistered); 
        
        conn.commit();

        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            
            assertTrue(rs.next());
        }
    }

    @Test
    public void testUsernameAlreadyTaken() throws SQLException {
        // Test case for trying to register with an existing username
        User user = new User("Ashen Perera", "456 Kdy St", "987654321V", "0779876543", "perera", "Ashen1234");

        boolean isRegistered = registerUser(user);

        assertFalse(isRegistered);
    }

    @Test
    public void testPasswordConfirmationMismatch() {
        User user = new User("Gunathilaka", "789 Mtl St", "456123789V", "0771122334", "alicebrown", "password123");
        
        boolean isValidPassword = confirmPasswordCheck(user.getPassword(), "password321");  // Mismatch

        assertFalse(isValidPassword);
    }

    @Test
    public void testPasswordAndConfirmPasswordMatch() {
        User user = new User("Charlie White", "101 Pine St", "321456987V", "0775566778", "charliewhite", "securepass123");

        boolean isValidPassword = confirmPasswordCheck(user.getPassword(), "securepass123");  // Match

        assertTrue(isValidPassword);
    }

    private boolean registerUser(User user) throws SQLException {
        String query = "INSERT INTO users (name, address, nic, telephone, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
        stmt.setString(1, user.getUsername());
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return false;  // Username already exists, registration fails
        }

        stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getAddress());
        stmt.setString(3, user.getNic());
        stmt.setString(4, user.getTelephone());
        stmt.setString(5, user.getUsername());
        stmt.setString(6, user.getPassword());

        int rowsAffected = stmt.executeUpdate();
        
        return rowsAffected > 0;
    }

    private boolean confirmPasswordCheck(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    @After
    public void tearDown() throws SQLException {
    	if (conn != null) {
            conn.commit(); 
            conn.setAutoCommit(true); 
            conn.close();
        }
    }
}

