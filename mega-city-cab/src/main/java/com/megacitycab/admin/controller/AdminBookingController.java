package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.admin.service.DriverServiceImpl;
import com.megacitycab.admin.service.VehicleServiceImpl;
import com.megacitycab.model.Booking;
import com.megacitycab.model.Driver;
import com.megacitycab.model.User;
import com.megacitycab.model.Vehicle;
import com.megacitycab.service.BookingServiceImpl;
import com.megacitycab.service.UserServiceImpl;

/**
 * Servlet implementation class AdminBookingController
 */
@WebServlet("/admin/bookings")
public class AdminBookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookingServiceImpl bookingService = new BookingServiceImpl();
	private DriverServiceImpl driverService = new DriverServiceImpl();
	private VehicleServiceImpl vehicleService = new VehicleServiceImpl();
	private UserServiceImpl userService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBookingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");

	    if (status == null || status.isEmpty()) {
	        response.sendRedirect(request.getContextPath() + "/admin"); 
	        return;  
	    }
	    
	    status = status.toLowerCase();

	    List<Booking> bookings = new ArrayList<>();
	    
	    if (status.equals("pending") || status.equals("accepted") || status.equals("declined")) {
            bookings = bookingService.getBookingsByStatus(status);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }

        if (bookings == null) {
            bookings = new ArrayList<>();
        }

        for (Booking booking : bookings) {
            User user = userService.getUserById(booking.getCustomerId());
            booking.setUser(user);

            if (status.equals("accepted")) {
                if (booking.getDriverId() > 0) {
                    Driver driver = driverService.getDriverById(booking.getDriverId());
                    booking.setDriver(driver);
                }
                if (booking.getVehicleId() > 0) {
                    Vehicle vehicle = vehicleService.getVehicleById(booking.getVehicleId());
                    booking.setVehicle(vehicle);
                }
            }
        }

	    request.setAttribute("status", status);
	    request.setAttribute("bookings", bookings);
	    request.getRequestDispatcher("/WEB-INF/views/admin/bookings.jsp").forward(request, response);
	}
}
