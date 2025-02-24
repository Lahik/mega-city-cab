package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.List;

import com.megacitycab.model.Driver;  

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.admin.service.DriverServiceImpl;

/**
 * Servlet implementation class DriverDeleteController
 */
@WebServlet("/admin/drivers/delete")
public class DriverDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DriverServiceImpl driverService = new DriverServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");

            if (id == null || id.isEmpty()) {
                setErrorAndForward(request, response, "Driver ID is missing.");
                return;
            }

            int driverId = Integer.parseInt(id);

            Driver driver = driverService.getDriverById(driverId);
            if (driver != null) {
                driverService.deleteDriver(driver);
                request.setAttribute("messages", List.of("Driver deleted successfully!"));
                request.setAttribute("messageType", "success");
                
                List<Driver> drivers = driverService.getAllDrivers();
                request.setAttribute("drivers", drivers); 
            } else {
                setErrorAndForward(request, response, "Driver not found");
                return;
            }

            request.getRequestDispatcher("/WEB-INF/views/admin/drivers.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            setErrorAndForward(request, response, "Invalid driver ID format");
        } catch (Exception e) {
            e.printStackTrace();
            setErrorAndForward(request, response, "Something went wrong while deleting the driver");
        }
    }

    private void setErrorAndForward(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("messages", List.of(errorMessage));
        request.setAttribute("messageType", "error");
        
        List<Driver> drivers = driverService.getAllDrivers();
        request.setAttribute("drivers", drivers); 
        request.getRequestDispatcher("/WEB-INF/views/admin/drivers.jsp").forward(request, response);
    }}
