package com.megacitycab.dao;

import com.megacitycab.database.DBConnectionFactory;
import com.megacitycab.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingDAOImpl implements BookingDAO {
    
    @Override
    public boolean insertBooking(Booking booking) {
        String query = "INSERT INTO bookings (customer_id, pickup_location, destination, pickup_date_time, no_of_seats, message) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, booking.getCustomerId());
            stmt.setString(2, booking.getPickupLocation());
            stmt.setString(3, booking.getDestination());
            stmt.setObject(4, booking.getPickupDateTime());
            stmt.setInt(5, Integer.parseInt(booking.getNoOfSeats()));
            stmt.setString(6, booking.getMessage());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
