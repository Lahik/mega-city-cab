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
import com.megacitycab.admin.service.FareSettingsServiceImpl;
import com.megacitycab.admin.service.VehicleServiceImpl;
import com.megacitycab.dao.BookingDAOImpl;
import com.megacitycab.model.Booking;
import com.megacitycab.model.Driver;
import com.megacitycab.model.FareSettings;
import com.megacitycab.model.Vehicle;
import com.megacitycab.service.BookingServiceImpl;

/**
 * Servlet implementation class AdminBookingAssignController
 */
@WebServlet("/admin/bookings/assign")
public class AdminBookingAssignController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FareSettingsServiceImpl fareSettingsService = new FareSettingsServiceImpl();
	private VehicleServiceImpl vehicleService = new VehicleServiceImpl();
	private DriverServiceImpl driverService = new DriverServiceImpl();
	private BookingServiceImpl bookingService = new BookingServiceImpl();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBookingAssignController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingIdStr = request.getParameter("id");

        if (bookingIdStr == null) {
            redirectToPending(request, response);
            return;
        }

        try {
            int bookingId = Integer.parseInt(bookingIdStr);
            Booking booking = new BookingDAOImpl().getBookingById(bookingId);

            if (booking == null || !"pending".equalsIgnoreCase(booking.getBookingStatus())) {
                redirectToPending(request, response);
                return;
            }
            
            int requiredSeats = Integer.parseInt(booking.getNoOfSeats());

            forwardToBookingAssign(request, response, bookingId, requiredSeats, null);
            
        } catch (NumberFormatException e) {
            redirectToPending(request, response);
            return;
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vehicleIdStr = request.getParameter("vehicle_id");
	    String driverIdStr = request.getParameter("driver_id");
	    String distanceStr = request.getParameter("distance");
	    String ratePerKmStr = request.getParameter("rate_per_km");
	    int bookingId = Integer.parseInt(request.getParameter("booking_id"));
	    int requiredSeats = Integer.parseInt(request.getParameter("required_seats"));

	    boolean hasErrors = false;
	    List<String> errorMessages = new ArrayList<String>();

	    if (vehicleIdStr == null || vehicleIdStr.isEmpty()) {
	        hasErrors = true;
	        errorMessages.add("Vehicle is required");
	    } 
	    if (driverIdStr == null || driverIdStr.isEmpty()) {
	        hasErrors = true;
	        errorMessages.add("Driver is required");
	    }
	    if (distanceStr == null || distanceStr.isEmpty()) {
	        hasErrors = true;
	        errorMessages.add("Distance is required");
	    } 
	    if (ratePerKmStr == null || ratePerKmStr.isEmpty()) {
	        hasErrors = true;
	        errorMessages.add("Rate per kilometer is required");
	    }

	    if (!hasErrors) {
	        try {
	            int vehicleId = Integer.parseInt(vehicleIdStr);
	            int driverId = Integer.parseInt(driverIdStr);
	            double distance = Double.parseDouble(distanceStr);
	            double ratePerKm = Double.parseDouble(ratePerKmStr);

	            if (distance <= 0) {
	                hasErrors = true;
	                errorMessages.add("Distance must be greater than zero");
	            } else if (ratePerKm < 10) {
	                hasErrors = true;
	                errorMessages.add("Rate per kilometer must be greater than 10");
	            }

	            if (!hasErrors) {
	            	int fare = getNetFare(distance, ratePerKm);
	                Booking booking = new Booking();
	                booking.setId(bookingId);
	                booking.setVehicleId(vehicleId);
	                booking.setDriverId(driverId);
	                booking.setFare(fare);
	                
	                boolean bookingAssigned = bookingService.assignBooking(booking);

	                if (bookingAssigned) {
	                	response.sendRedirect(request.getContextPath() + "/admin/bookings?status=accepted");
	                    return;
	                } else {
	                    hasErrors = true;
	                    errorMessages.add("Failed to assign the booking. Please try again later");
	                }
	            }
	        } catch (NumberFormatException e) {
	            hasErrors = true;
	            errorMessages.add("Invalid input format. Please check your inputs");
	        }
	    }
	    
	    if(hasErrors){
	        forwardToBookingAssign(request, response, bookingId, requiredSeats, errorMessages);
	    }
	}

	private void redirectToPending(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/admin/bookings?status=pending");
    }
	
	private int getNetFare(double distance, double ratePerKm) {
		FareSettings fareSettings = fareSettingsService.getFareSettings();
        double baseFare = fareSettings.getBaseFare();
        double taxRate = fareSettings.getTaxRate();
        double discountRate = fareSettings.getDiscountRate();
        
        double grossFare = baseFare + (distance * ratePerKm);
        double taxAmount = (grossFare * taxRate) / 100;
        double discountAmount = (grossFare * discountRate) / 100;
        double netFare = (grossFare + taxAmount) - discountAmount;
        netFare = Math.floor(netFare / 10) * 10;
        
        return (int)netFare;
	}
	
	private void forwardToBookingAssign(HttpServletRequest request, HttpServletResponse response, int bookingId, int requiredSeats, List<String> errorMessages) throws ServletException, IOException {
		List<Vehicle> availableVehicles = vehicleService.getVehiclesWithMinSeats(requiredSeats);
        List<Driver> availableDrivers = driverService.getAllDrivers();
        FareSettings fareSettings = fareSettingsService.getFareSettings();
        
		request.setAttribute("requiredSeats", requiredSeats);
        request.setAttribute("bookingId", bookingId);
        request.setAttribute("fareSettings", fareSettings);
        request.setAttribute("availableVehicles", availableVehicles);
        request.setAttribute("availableDrivers", availableDrivers);
        
        request.setAttribute("messages", errorMessages);
        request.setAttribute("messageType", errorMessages != null ? "error" : "");

        request.getRequestDispatcher("/WEB-INF/views/admin/booking-assign.jsp").forward(request, response);
	}
}
