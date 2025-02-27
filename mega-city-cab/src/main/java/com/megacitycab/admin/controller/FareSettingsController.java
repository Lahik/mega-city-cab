package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megacitycab.admin.service.FareSettingsServiceImpl;
import com.megacitycab.model.Admin;
import com.megacitycab.model.FareSettings;

/**
 * Servlet implementation class FareSettingsController
 */
@WebServlet("/admin/fare")
public class FareSettingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FareSettingsServiceImpl fareSettingsService = new FareSettingsServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FareSettingsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        Admin sessionAdmin = (Admin) session.getAttribute("admin");

        if (sessionAdmin == null || sessionAdmin.getId() != 1) {
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }
	    
        request.setAttribute("fareSettings", fareSettingsService.getFareSettings());
        request.setAttribute("fareSettingsHistory", fareSettingsService.getFareUpdateHistory());
        request.getRequestDispatcher("/WEB-INF/views/admin/fare-settings.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Admin sessionAdmin = (Admin) session.getAttribute("admin");

        if (sessionAdmin == null || sessionAdmin.getId() != 1) {
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }

	    fareSettingsService = new FareSettingsServiceImpl();

	    int baseFare = Integer.parseInt(request.getParameter("base_fare"));
	    double taxRate = Double.parseDouble(request.getParameter("tax_rate"));
	    double discountRate = Double.parseDouble(request.getParameter("discount_rate"));

	    fareSettingsService.updateFareSettings(new FareSettings(baseFare, taxRate, discountRate));

	    request.setAttribute("messages", List.of("Vehicle added successfully!"));
        request.setAttribute("messageType", "success");
        
        request.setAttribute("fareSettings", fareSettingsService.getFareSettings());
        request.setAttribute("fareSettingsHistory", fareSettingsService.getFareUpdateHistory());
        request.getRequestDispatcher("/WEB-INF/views/admin/fare-settings.jsp").forward(request, response);
	}

}
