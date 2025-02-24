package com.megacitycab.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.admin.service.VehicleServiceImpl;
import com.megacitycab.model.Vehicle;

/**
 * Servlet implementation class VehicleUpdateController
 */
@WebServlet("/admin/vehicles/update")
public class VehicleUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleServiceImpl vehicleService = new VehicleServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
		if (idParam == null || idParam.isEmpty()) {
		    response.sendRedirect(request.getContextPath() + "/admin/vehicles");
		    return;
		}
		
		int id = -1;
		try {
		    id = Integer.parseInt(idParam);
		} catch (NumberFormatException e) {
		    response.sendRedirect(request.getContextPath() + "/admin/vehicles");
		    return;
		}
		
		Vehicle vehicle = vehicleService.getVehicleById(id);
		
		if(vehicle != null) {
			request.setAttribute("vehicle", vehicle);
			request.getRequestDispatcher("/WEB-INF/views/admin/vehicle-update.jsp").forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/vehicles");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String vehicleNumber = request.getParameter("vehicle_number").trim();
        String vehicleType = request.getParameter("vehicle_type").trim();
        String seats = request.getParameter("seats").trim();

	    Vehicle updatedVehicle = new Vehicle(id, vehicleNumber, vehicleType, seats);

	    vehicleService.updateVehicle(updatedVehicle);

        response.sendRedirect(request.getContextPath() + "/admin/vehicles");
	}

}
