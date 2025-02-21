package com.megacity.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacity.admin.service.AdminServiceImpl;
import com.megacitycab.model.Admin;
import com.megacitycab.validation.UsernameValidator;

/**
 * Servlet implementation class AdminLoginController
 */
@WebServlet("/admin/login")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username").trim();
	    String password = request.getParameter("password");

	    AdminServiceImpl adminService = new AdminServiceImpl();
	    UsernameValidator usernameValidator = new UsernameValidator();
	    
	    if(!usernameValidator.validate(username)) {
	    	request.setAttribute("messages", List.of(usernameValidator.getErrorMessage()));
        	request.setAttribute("messageType", "error");

	        request.getRequestDispatcher("/WEB-INF/views/admin/admin-login.jsp").forward(request, response);
	        return;
	    }
	    
	    if(adminService.authenticateUser(username, password)) {
	    	Admin admin = adminService.getUserDetails(username);
	        request.getSession().setAttribute("admin", admin);

	        response.sendRedirect(request.getContextPath() + "/admin");
	    } else {
	        request.setAttribute("username", username);
	        request.setAttribute("messages", List.of("Invalid Credentials!"));
        	request.setAttribute("messageType", "error");

	        request.getRequestDispatcher("/WEB-INF/views/admin/admin-login.jsp").forward(request, response);
	    }
	}

}
