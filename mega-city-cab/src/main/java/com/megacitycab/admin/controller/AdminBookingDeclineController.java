package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.model.Booking;
import com.megacitycab.service.BookingServiceImpl;

/**
 * Servlet implementation class AdminBookingDeclineController
 */
@WebServlet("/admin/bookings/decline")
public class AdminBookingDeclineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookingServiceImpl bookingService = new BookingServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBookingDeclineController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String bookingIdStr = request.getParameter("id");

            if (bookingIdStr == null || bookingIdStr.isEmpty()) {
                setErrorAndForward(request, response, "Booking ID is missing.");
                return;
            }

            int bookingId = Integer.parseInt(bookingIdStr);

            Booking booking = bookingService.getBookingById(bookingId);
            if (booking != null) {
                bookingService.declineBooking(bookingId);
                request.setAttribute("messages", List.of("Booking declined successfully!"));
                request.setAttribute("messageType", "success");

                List<Booking> bookings = bookingService.getBookingsByStatus("pending");
                request.setAttribute("bookings", bookings);
            } else {
                setErrorAndForward(request, response, "Booking not found.");
                return;
            }

            request.getRequestDispatcher("/WEB-INF/views/admin/bookings.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            setErrorAndForward(request, response, "Invalid booking ID format.");
        } catch (Exception e) {
            e.printStackTrace();
            setErrorAndForward(request, response, "Something went wrong while declining the booking.");
        }
    }

    private void setErrorAndForward(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("messages", List.of(errorMessage));
        request.setAttribute("messageType", "error");

        List<Booking> bookings = bookingService.getBookingsByStatus("pending");
        request.setAttribute("bookings", bookings);
        
        request.getRequestDispatcher("/WEB-INF/views/admin/bookings.jsp").forward(request, response);
    }
}
