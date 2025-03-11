package com.megacitycab.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.model.Booking;
import com.megacitycab.model.User;
import com.megacitycab.service.BookingServiceImpl;
import com.megacitycab.service.ValidationService;
import com.megacitycab.util.UserSessionUtils;

/**
 * Servlet implementation class BookingController
 */
@WebServlet("/booking")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ValidationService validationService = new ValidationService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/booking.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loggedInUser = UserSessionUtils.getLoggedInUser(request);
		if (loggedInUser == null) {
		    response.sendRedirect(request.getContextPath() + "/login");
		    return;
		}
        
		String pickupLocation = request.getParameter("pickup_location").trim();
        String destination = request.getParameter("destination").trim();
        String pickupDate = request.getParameter("pickup_date").trim(); 
        String pickupTime = request.getParameter("pickup_time").trim(); 
        String seats = request.getParameter("seats").trim();  
        String message = request.getParameter("message").trim(); 
        
        Timestamp pickupDateTime = Timestamp.valueOf(LocalDateTime.parse(pickupDate + " " + pickupTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        
        boolean isValid = true;
        List<String> errorMessages = new ArrayList<String>();
        
        if (!validationService.validate("pickup_date", pickupDate)) {
        	errorMessages.add(validationService.getErrorMessage("pickup_date"));
        	isValid = false;
        }
        
        LocalDate currentDateSL = ZonedDateTime.now(ZoneId.of("Asia/Colombo")).toLocalDate();
        LocalDate parsedPickupDate = LocalDate.parse(pickupDate);
        if(parsedPickupDate.equals(currentDateSL) && !validationService.validate("pickup_time", pickupTime)) {
        	errorMessages.add(validationService.getErrorMessage("pickup_time"));
        	isValid = false;
        }

        if (!validationService.validate("seats", seats)) {
        	errorMessages.add(validationService.getErrorMessage("seats"));
        	isValid = false; 
        }
        
        if(isValid) {
            int userId = loggedInUser.getId();
        	Booking booking = new Booking();
            booking.setCustomerId(userId);
            booking.setPickupLocation(pickupLocation);
            booking.setDestination(destination);
            booking.setPickupDateTime(pickupDateTime);
            booking.setNoOfSeats(seats);
            booking.setMessage(message);

            BookingServiceImpl bookingService = new BookingServiceImpl();
            boolean bookingSuccess = bookingService.createBooking(booking);

            if (bookingSuccess) {
            	response.sendRedirect(request.getContextPath() + "/my-bookings");
            } else {
                errorMessages.add("Booking could not be processed. Please try again.");
                request.setAttribute("messages", errorMessages);
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher("/WEB-INF/views/booking.jsp").forward(request, response);
            }
        }else {
        	request.setAttribute("pickup_location", pickupLocation);
        	request.setAttribute("destination", destination);
        	request.setAttribute("pickup_date", pickupDate);
        	request.setAttribute("pickup_time", pickupTime);
        	request.setAttribute("seats", seats);
        	request.setAttribute("message", message);
        	
        	request.setAttribute("messages", errorMessages);
        	request.setAttribute("messageType", "error");
        	
        	request.getRequestDispatcher("/WEB-INF/views/booking.jsp").forward(request, response);
        }
	}
	
}
