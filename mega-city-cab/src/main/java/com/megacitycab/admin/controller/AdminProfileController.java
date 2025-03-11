package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.admin.service.AdminServiceImpl;
import com.megacitycab.model.Admin;
import com.megacitycab.util.AdminSessionUtils;
import com.megacitycab.util.PasswordHasher;
import com.megacitycab.validation.PasswordValidator;
import com.megacitycab.validation.UsernameValidator;

/**
 * Servlet implementation class AdminUpdateProfile
 */
@WebServlet("/admin/profile")
public class AdminProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminServiceImpl adminService = new AdminServiceImpl();
        
        Admin loggedInAdmin = AdminSessionUtils.getLoggedInAdmin(request);
        int adminId = loggedInAdmin.getId();
        
        String username = request.getParameter("username").trim();
        String currentPassword = request.getParameter("current_password");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");
        
        UsernameValidator usernameValidator = new UsernameValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        
        if (!usernameValidator.validate(username)) {
            setErrorAndForward(request, response, usernameValidator.getErrorMessage());
            return;
        }

        if (!passwordValidator.confirmPassword(newPassword, confirmPassword)) {
            setErrorAndForward(request, response, passwordValidator.getErrorMessage());
            return;
        }
        
        if (adminService.isUsernameTaken(username, adminId)) {
            setErrorAndForward(request, response, String.valueOf("Username already taken!"));
            return;
        }
        
        if (!PasswordHasher.verifyPassword(currentPassword, loggedInAdmin.getPassword())) {
            setErrorAndForward(request, response, "Current password is incorrect!");
            return;
        }
        
        if (adminService.updateProfile(new Admin(adminId, username, newPassword))) {
        	AdminSessionUtils.setLoggedInAdminInSession(request, adminService.getUserDetails(username));
            request.setAttribute("messages", List.of("Profile updated successfully!"));
            request.setAttribute("messageType", "success");
            request.getRequestDispatcher("/WEB-INF/views/admin/admin-profile.jsp").forward(request, response);
        } else {
            setErrorAndForward(request, response, "Failed to update profile. Please try again later.");
        }
    }
    
    private void setErrorAndForward(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("messages", List.of(errorMessage));
        request.setAttribute("messageType", "error");
        request.getRequestDispatcher("/WEB-INF/views/admin/admin-profile.jsp").forward(request, response);
    }
}

