package com.megacitycab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megacitycab.model.User;
import com.megacitycab.service.UserService;
import com.megacitycab.util.PasswordHasher;
import com.megacitycab.validation.PasswordValidator;

/**
 * Servlet implementation class PasswordUpdateController
 */
@WebServlet("/update-password")
public class PasswordUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User loggedInUser = (User) session.getAttribute("user");
        UserService userService = new UserService();
        List<String> messages = new ArrayList<>();
        String messageType = "error";
        PasswordValidator passwordValidator = new PasswordValidator();

        String currentPassword = request.getParameter("current_password");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("new_password_confirmation");

        if (!passwordValidator.confirmPassword(newPassword, confirmPassword)) {
            messages.add(passwordValidator.getErrorMessage());
        } else if (!PasswordHasher.verifyPassword(currentPassword, loggedInUser.getPassword())) {
            messages.add("Current password is incorrect!");
        } else {
            loggedInUser.setPassword(PasswordHasher.hashPassword(newPassword));
            if (userService.updateUserPassword(loggedInUser)) {
            	session.setAttribute("user", loggedInUser);
                messages.add("Password updated successfully!");
                messageType = "success";
            } else {
                messages.add("Failed to update password. Please try again.");
            }
        }

        request.setAttribute("messages", messages);
        request.setAttribute("messageType", messageType);
        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
	}

}
