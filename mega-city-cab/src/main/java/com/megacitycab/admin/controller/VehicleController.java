package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.admin.service.VehicleServiceImpl;
import com.megacitycab.model.Vehicle;

/**
 * Servlet implementation class VehicleController
 */
@WebServlet("/admin/vehicles")
public class VehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleServiceImpl vehicleService = new VehicleServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Vehicle> vehicles = vehicleService.getAllVehicles();
		if (vehicles == null){
		    vehicles = new ArrayList<>();
		}
		request.setAttribute("vehicles", vehicles);
		request.getRequestDispatcher("/WEB-INF/views/admin/vehicles.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vehicleNumber = request.getParameter("vehicle_number").trim();
        String vehicleType = request.getParameter("vehicle_type").trim();
        String seats = request.getParameter("seats").trim();
        
        Vehicle vehicle = new Vehicle(vehicleNumber, vehicleType, seats);

        boolean success = vehicleService.addVehicle(vehicle);

        if (success) {
            request.setAttribute("messages", List.of("Vehicle added successfully!"));
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("messages", List.of("Failed to add Vehicle. Try again."));
            request.setAttribute("messageType", "error");
        }
        
        doGet(request, response);
	}

}
