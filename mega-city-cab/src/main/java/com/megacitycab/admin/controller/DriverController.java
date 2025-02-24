package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.megacitycab.model.Driver;

import com.megacitycab.admin.service.DriverServiceImpl;

/**
 * Servlet implementation class DriverController
 */
@WebServlet("/admin/drivers")
public class DriverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DriverServiceImpl driverService = new DriverServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Driver> drivers = driverService.getAllDrivers();
		if (drivers == null) {
		    drivers = new ArrayList<>();
		}
		request.setAttribute("drivers", drivers);
		request.getRequestDispatcher("/WEB-INF/views/admin/drivers.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name").trim();
        String licenseNumber= request.getParameter("license_number").trim();
        
        Driver driver = new Driver();
        driver.setName(name);
        driver.setLicenseNumber(licenseNumber);

        boolean success = driverService.addDriver(driver);

        if (success) {
            request.setAttribute("messages", List.of("Driver added successfully!"));
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("messages", List.of("Failed to add driver. Try again."));
            request.setAttribute("messageType", "error");
        }
        
        doGet(request, response);
	}

}
