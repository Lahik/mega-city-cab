package com.megacitycab.service;

import java.util.List;

import com.megacitycab.model.Booking;

public interface BookingService {
    boolean createBooking(Booking booking);
    List<Booking> getBookingsByStatus(String status);
    Booking getBookingById(int id);
    void declineBooking(int id);
    boolean assignBooking(Booking booking);
}
