package com.megacitycab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megacitycab.model.Booking;
import com.megacitycab.model.User;
import com.megacitycab.service.BookingServiceImpl;

/**
 * Servlet implementation class MyBookingsController
 */
@WebServlet("/my-bookings")
public class MyBookingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookingServiceImpl bookingService = new BookingServiceImpl(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBookingsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        
        if (session != null) {
            User user = (User) session.getAttribute("user");
            
            if (user != null) {
                int userId = user.getId();
                List<Booking> booking = bookingService.getBookingsByUserId(userId);
                
                request.setAttribute("bookings", booking);
                request.getRequestDispatcher("/WEB-INF/views/my-bookings.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + "/login");
	}
}
