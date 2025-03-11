package com.megacitycab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.megacitycab.database.DBConnectionFactory;
import com.megacitycab.model.User;
import com.megacitycab.util.PasswordHasher;

public class UserDAOImpl implements UserDAO{

	@Override
    public boolean insertUser(User user) {
        String query = "INSERT INTO users (name, address, nic, telephone, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getNic());
            stmt.setString(4, user.getTelephone());
            stmt.setString(5, user.getUsername());
            stmt.setString(6, user.getPassword());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
	@Override
    public boolean validateCredentials(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";
        
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
    public User findUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                	rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("nic"),
                    rs.getString("telephone"),
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
    public boolean isUsernameTaken(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
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
    public boolean isUsernameTaken(String username, int id) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND id != ?";
        
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
    public boolean updateUserDetails(User user) {
        String query = "UPDATE users SET name = ?, address = ?, nic = ?, telephone = ?, username = ? WHERE id = ?";
        
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getAddress());
            stmt.setString(3, user.getNic());
            stmt.setString(4, user.getTelephone());
            stmt.setString(5, user.getUsername());
            stmt.setInt(6, user.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
	@Override
    public boolean changeUserPassword(User user) {
        String query = "UPDATE users SET password = ? WHERE id = ?";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, user.getPassword());
            pstmt.setInt(2, user.getId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection conn = DBConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("nic"),
                        rs.getString("telephone"),
                        "",
                        ""
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
	}

	@Override
	public void deleteUser(int id) {
		String query = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public User getUserById(int id) {
		String query = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("nic"),
                            rs.getString("telephone"),
                            rs.getString("username")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

}
