package com.megacitycab.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.megacitycab.database.DBConnectionFactory;
import com.megacitycab.model.Admin;
import com.megacitycab.util.PasswordHasher;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public boolean insertUser(Admin admin) {
		String query = "INSERT INTO admins (username, password) VALUES (?, ?)";
        
        try (Connection conn = DBConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
        	
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
	}

	@Override
	public boolean validateCredentials(String username, String password) {
		String query = "SELECT password FROM admins WHERE username = ?";
        
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                return PasswordHasher.verifyPassword(password, hashedPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public Admin findUserByUsername(String username) {
		String query = "SELECT * FROM admins WHERE username = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                	rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;    
	}

	@Override
	public boolean isUsernameTaken(String username, int id) {
		String query = "SELECT COUNT(*) FROM admins WHERE username = ? AND id != ?";
        
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setInt(2, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean updateAccount(Admin admin) {
		 String query = "UPDATE admins SET username = ?, password = ? WHERE id = ?";
	        
	        try (Connection conn = DBConnectionFactory.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {

	            stmt.setString(1, admin.getUsername());
	            stmt.setString(2, PasswordHasher.hashPassword(admin.getPassword()));
	            stmt.setInt(3, admin.getId());

	            int rowsUpdated = stmt.executeUpdate();
	            return rowsUpdated > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	}
}
