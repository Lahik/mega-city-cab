package com.megacitycab.service;

import com.megacitycab.dao.BookingDAO;
import com.megacitycab.dao.BookingDAOImpl;
import com.megacitycab.model.Booking;

public class BookingServiceImpl implements BookingService {
    
    private BookingDAO bookingDAO;

    public BookingServiceImpl() {
        this.bookingDAO = new BookingDAOImpl();
    }

    @Override
    public boolean createBooking(Booking booking) {
        return bookingDAO.insertBooking(booking);
    }
}
