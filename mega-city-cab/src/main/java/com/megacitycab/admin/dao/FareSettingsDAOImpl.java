package com.megacitycab.admin.dao;

import com.megacitycab.database.DBConnectionFactory;
import com.megacitycab.model.FareSettings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FareSettingsDAOImpl implements FareSettingsDAO {

    @Override
    public FareSettings getFareSettings() {
        String query = "SELECT * FROM fare_settings WHERE id = 1";
        try (Connection conn = DBConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                return new FareSettings(
                    rs.getInt("base_fare"),
                    rs.getDouble("tax_rate"),
                    rs.getDouble("discount_rate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateFareSettings(FareSettings newFare) {
        String updateQuery = "UPDATE fare_settings SET base_fare=?, tax_rate=?, discount_rate=? WHERE id=1";
        String historyQuery = "INSERT INTO fare_settings_history (base_fare, tax_rate, discount_rate) VALUES (?,?,?)";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
             PreparedStatement historyStmt = conn.prepareStatement(historyQuery)) {

            conn.setAutoCommit(false);

            updateStmt.setInt(1, newFare.getBaseFare());
            updateStmt.setDouble(2, newFare.getTaxRate());
            updateStmt.setDouble(3, newFare.getDiscountRate());
            updateStmt.executeUpdate();

            historyStmt.setInt(1, newFare.getBaseFare());
            historyStmt.setDouble(2, newFare.getTaxRate());
            historyStmt.setDouble(3, newFare.getDiscountRate());
            historyStmt.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FareSettings> getFareUpdateHistory() {
        List<FareSettings> history = new ArrayList<>();
        String query = "SELECT * FROM fare_settings_history ORDER BY update_time DESC";

        try (Connection conn = DBConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                history.add(new FareSettings(
                    rs.getInt("base_fare"),
                    rs.getDouble("tax_rate"),
                    rs.getDouble("discount_rate"),
                    rs.getTimestamp("update_time")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}
