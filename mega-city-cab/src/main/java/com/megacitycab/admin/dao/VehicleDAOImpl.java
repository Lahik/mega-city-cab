package com.megacitycab.admin.dao;

import com.megacitycab.model.Vehicle;
import com.megacitycab.database.DBConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (vehicle_number, vehicle_type, no_of_seats) VALUES (?, ?, ?)";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, vehicle.getVehicleNumber());
            stmt.setString(2, vehicle.getVehicleType());
            stmt.setString(3, vehicle.getNoOfSeats());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Vehicle getVehicleById(int id) {
        String query = "SELECT * FROM vehicles WHERE id = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Vehicle(
                            rs.getInt("id"),
                            rs.getString("vehicle_number"),
                            rs.getString("vehicle_type"),
                            rs.getString("no_of_seats")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";
        try (Connection conn = DBConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getInt("id"),
                        rs.getString("vehicle_number"),
                        rs.getString("vehicle_type"),
                        rs.getString("no_of_seats")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        String query = "UPDATE vehicles SET vehicle_number = ?, vehicle_type = ?, no_of_seats = ? WHERE id = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, vehicle.getVehicleNumber());
            stmt.setString(2, vehicle.getVehicleType());
            stmt.setString(3, vehicle.getNoOfSeats());
            stmt.setInt(4, vehicle.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVehicle(int id) {
        String query = "DELETE FROM vehicles WHERE id = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Vehicle> getVehiclesWithMinSeats(int seats) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE no_of_seats >= ? ORDER BY no_of_seats ASC";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, seats);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(new Vehicle(
                            rs.getInt("id"),
                            rs.getString("vehicle_number"),
                            rs.getString("vehicle_type"),
                            rs.getString("no_of_seats")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

}
