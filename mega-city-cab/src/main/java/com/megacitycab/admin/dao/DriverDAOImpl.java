package com.megacitycab.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.megacitycab.database.DBConnectionFactory;
import com.megacitycab.model.Driver;

public class DriverDAOImpl implements DriverDAO {
    
    @Override
    public boolean insertDriver(Driver driver) {
        String query = "INSERT INTO drivers (name, license_number) VALUES (?, ?)";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM drivers";
        
        try (Connection conn = DBConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
             
            while (rs.next()) {
                drivers.add(new Driver(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("license_number")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    @Override
    public Driver getDriverById(int id) {
        String query = "SELECT * FROM drivers WHERE id = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Driver(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("license_number")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    @Override
    public void updateDriver(Driver driver) {
        String query = "UPDATE drivers SET name = ?, license_number = ? WHERE id = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setInt(3, driver.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDriver(Driver driver) {
        String query = "DELETE FROM drivers WHERE id = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, driver.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}