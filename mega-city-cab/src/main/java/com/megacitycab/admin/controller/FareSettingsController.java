package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.admin.service.FareSettingsServiceImpl;
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
        request.setAttribute("fareSettings", fareSettingsService.getFareSettings());
        request.setAttribute("fareSettingsHistory", fareSettingsService.getFareUpdateHistory());
        request.getRequestDispatcher("/WEB-INF/views/admin/fare-settings.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int baseFare = Integer.parseInt(request.getParameter("base_fare"));
	    double taxRate = Double.parseDouble(request.getParameter("tax_rate"));
	    double discountRate = Double.parseDouble(request.getParameter("discount_rate"));

	    fareSettingsService.updateFareSettings(new FareSettings(baseFare, taxRate, discountRate));

	    request.setAttribute("messages", List.of("Fare Settings Updated Successfully"));
        request.setAttribute("messageType", "success");
        
        request.setAttribute("fareSettings", fareSettingsService.getFareSettings());
        request.setAttribute("fareSettingsHistory", fareSettingsService.getFareUpdateHistory());
        request.getRequestDispatcher("/WEB-INF/views/admin/fare-settings.jsp").forward(request, response);
	}

}
