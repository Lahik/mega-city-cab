package com.megacitycab.dao;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.megacitycab.model.Booking;

public class BookingDAOImplTest {

    private BookingDAOImpl bookingDAO;

    @Before
    public void setup() {
        bookingDAO = new BookingDAOImpl();
    }

    @Test
    public void testCreateBooking() {
        Booking booking = new Booking();
        booking.setCustomerId(8);
        booking.setPickupLocation("Test Location");
        booking.setDestination("Test Destination");

        LocalDateTime pickupDateTime = LocalDateTime.now().plusDays(1); 
        Timestamp timestamp = Timestamp.valueOf(pickupDateTime);
        booking.setPickupDateTime(timestamp);  
        booking.setNoOfSeats("4");
        booking.setMessage("Test Message");

        boolean insertSuccess = bookingDAO.insertBooking(booking);
        assertTrue("Booking success", insertSuccess);
    }
}
