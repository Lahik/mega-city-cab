package com.megacitycab.dao;

import java.util.List;

import com.megacitycab.model.Booking;

public interface BookingDAO {
    boolean insertBooking(Booking booking);
    List<Booking> getBookingsByStatus(String status);
    Booking getBookingById(int id);
    void declineBooking(int id);
    boolean assignBooking(Booking booking);
    List<Booking> getBookingsByUserId(int id);
}