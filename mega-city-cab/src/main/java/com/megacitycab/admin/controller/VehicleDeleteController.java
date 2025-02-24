package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.admin.service.VehicleServiceImpl;
import com.megacitycab.model.Vehicle;

/**
 * Servlet implementation class VehicleDeleteController
 */
@WebServlet("/admin/vehicles/delete")
public class VehicleDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VehicleServiceImpl vehicleService = new VehicleServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            String id = request.getParameter("id");

            if (id == null || id.isEmpty()) {
                setErrorAndForward(request, response, "Vehicle ID is missing.");
                return;
            }

            int vehicleId = Integer.parseInt(id);

            Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
            if (vehicle != null) {
                vehicleService.deleteVehicle(vehicleId);
                request.setAttribute("messages", List.of("Vehicle deleted successfully!"));
                request.setAttribute("messageType", "success");
                
                List<Vehicle> vehicles = vehicleService.getAllVehicles();
                request.setAttribute("vehicles", vehicles); 
            } else {
                setErrorAndForward(request, response, "Vehicle not found");
                return;
            }

            request.getRequestDispatcher("/WEB-INF/views/admin/vehicles.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            setErrorAndForward(request, response, "Invalid vehicle ID format");
        } catch (Exception e) {
            e.printStackTrace();
            setErrorAndForward(request, response, "Something went wrong while deleting the vehicle");
        }
    }

    private void setErrorAndForward(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("messages", List.of(errorMessage));
        request.setAttribute("messageType", "error");
        
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        request.setAttribute("vehicles", vehicles); 
        request.getRequestDispatcher("/WEB-INF/views/admin/vehicles.jsp").forward(request, response);
    }}
