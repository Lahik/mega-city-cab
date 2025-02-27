package com.megacitycab.service;

import java.util.List;

import com.megacitycab.dao.BookingDAOImpl;
import com.megacitycab.model.Booking;

public class BookingServiceImpl implements BookingService {
    
    private BookingDAOImpl bookingDAO;

    public BookingServiceImpl() {
        this.bookingDAO = new BookingDAOImpl();
    }

    @Override
    public boolean createBooking(Booking booking) {
        return bookingDAO.insertBooking(booking);
    }

	@Override
	public List<Booking> getBookingsByStatus(String status) {
		return bookingDAO.getBookingsByStatus(status);
	}

	@Override
	public Booking getBookingById(int id) {
		return bookingDAO.getBookingById(id);
	}

	@Override
	public void declineBooking(int id) {
		bookingDAO.declineBooking(id);
	}
	
	@Override
	public boolean assignBooking(Booking booking) {
		return bookingDAO.assignBooking(booking);
	}
}
