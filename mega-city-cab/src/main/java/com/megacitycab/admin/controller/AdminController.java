package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megacitycab.admin.service.AdminServiceImpl;
import com.megacitycab.model.Admin;
import com.megacitycab.util.PasswordHasher;
import com.megacitycab.validation.PasswordValidator;
import com.megacitycab.validation.UsernameValidator;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin/admins")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminServiceImpl adminService = new AdminServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Admin> admins = adminService.getAllAdmins();
		if (admins == null){
		    admins = new ArrayList<>();
		}
		request.setAttribute("admins", admins);
		request.getRequestDispatcher("/WEB-INF/views/admin/admins.jsp").forward(request, response);
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
		
		String username = request.getParameter("username").trim();
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        
        UsernameValidator usernameValidator = new UsernameValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        
        if (!usernameValidator.validate(username)) {
            setErrorAndForward(request, response, usernameValidator.getErrorMessage());
            return;
        }
        
        if (!passwordValidator.confirmPassword(password, confirmPassword)) {
            setErrorAndForward(request, response, passwordValidator.getErrorMessage());
            return;
        }
        
        if (adminService.isUsernameTaken(username, sessionAdmin.getId()) || username.equals(sessionAdmin.getUsername())) {
            setErrorAndForward(request, response, String.valueOf("Username already taken!"));
            return;
        }

        String hashedPassword = PasswordHasher.hashPassword(password);
        Admin admin = new Admin(username, hashedPassword);
        boolean success = adminService.createUser(admin);

        if (success) {
            request.setAttribute("messages", List.of("Admin added successfully!"));
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("messages", List.of("Failed to add Admin. Try again."));
            request.setAttribute("messageType", "error");
        }
        
        doGet(request, response);
	}
	
	private void setErrorAndForward(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("messages", List.of(errorMessage));
        request.setAttribute("messageType", "error");
        doGet(request, response);
    }
}
