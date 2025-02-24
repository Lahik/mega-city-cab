package com.megacitycab.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.admin.service.DriverServiceImpl;
import com.megacitycab.model.Driver;

/**
 * Servlet implementation class DriverUpdateController
 */
@WebServlet("/admin/drivers/update")
public class DriverUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DriverServiceImpl driverService = new DriverServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
		if (idParam == null || idParam.isEmpty()) {
		    response.sendRedirect(request.getContextPath() + "/admin/drivers");
		    return;
		}
		
		int id = -1;
		try {
		    id = Integer.parseInt(idParam);
		} catch (NumberFormatException e) {
		    response.sendRedirect(request.getContextPath() + "/admin/drivers");
		    return;
		}
		
		Driver driver = driverService.getDriverById(id);
		
		if(driver != null) {
			request.setAttribute("driver", driver);
			request.getRequestDispatcher("/WEB-INF/views/admin/driver-update.jsp").forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/drivers");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    String name = request.getParameter("name");
	    String licenseNumber = request.getParameter("license_number");

	    Driver updatedDriver = new Driver();
	    updatedDriver.setId(id);
	    updatedDriver.setName(name);
	    updatedDriver.setLicenseNumber(licenseNumber);

	    driverService.updateDriver(updatedDriver);

        response.sendRedirect(request.getContextPath() + "/admin/drivers");
	}


}
