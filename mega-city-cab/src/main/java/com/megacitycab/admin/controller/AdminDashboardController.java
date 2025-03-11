package com.megacitycab.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.service.BookingServiceImpl;
import com.megacitycab.util.CountService;

/**
 * Servlet implementation class AdminDashboardController
 */
@WebServlet("/admin")
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CountService countService = new CountService();
	private BookingServiceImpl bookingService = new BookingServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userCount = countService.getNoOfRecords("users");
        int adminCount = countService.getNoOfRecords("admins");
        int vehicleCount = countService.getNoOfRecords("vehicles");
        int driverCount = countService.getNoOfRecords("drivers");

        int pendingCount = bookingService.getBookingsByStatus("pending").size();
        int acceptedCount = bookingService.getBookingsByStatus("accepted").size();
        int declinedCount = bookingService.getBookingsByStatus("declined").size();

        request.setAttribute("userCount", userCount);
        request.setAttribute("adminCount", adminCount);
        request.setAttribute("vehicleCount", vehicleCount);
        request.setAttribute("driverCount", driverCount);
        
        request.setAttribute("pendingCount", pendingCount);
        request.setAttribute("acceptedCount", acceptedCount);
        request.setAttribute("declinedCount", declinedCount);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
	}
}
