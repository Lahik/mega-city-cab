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
import com.megacitycab.service.ValidationService;
import com.megacitycab.util.PasswordHasher;
import com.megacitycab.validation.PasswordValidator;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
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
        int userId = loggedInUser.getId();
		
		String name = request.getParameter("name").trim();
        String address = request.getParameter("address").trim();
        String nic = request.getParameter("nic").trim();
        String telephone = request.getParameter("telephone").trim();
        String username = request.getParameter("username").trim();
        
        boolean isValid = true;
        List<String> errorMessages = new ArrayList<String>();

        ValidationService validationService = new ValidationService();
        UserService userService = new UserService();
        
        if (!validationService.validate("nic", nic, null)) {
            errorMessages.add(validationService.getErrorMessage("nic"));
            isValid = false;
        }

        if (!validationService.validate("telephone", telephone, null)) {
            errorMessages.add(validationService.getErrorMessage("telephone"));
            isValid = false;
        }

        if (!validationService.validate("username", username, null)) {
            errorMessages.add(validationService.getErrorMessage("username"));
            isValid = false;
        }

        if (!validationService.validate("sanitization", name, null) || 
            !validationService.validate("sanitization", address, null)) {
            errorMessages.add(validationService.getErrorMessage("sanitization"));
            isValid = false;
        }
        
        if (userService.isUsernameTaken(username, userId)) {
            errorMessages.add("Username already taken!");
            isValid = false;
        }

        if (isValid) {
            loggedInUser.setName(name);
            loggedInUser.setAddress(address);
            loggedInUser.setNic(nic);
            loggedInUser.setTelephone(telephone);
            loggedInUser.setUsername(username);

            boolean updateSuccess = userService.updateUserProfile(loggedInUser);

            if (updateSuccess) {
                session.setAttribute("user", loggedInUser);
                request.setAttribute("messages", List.of("Profile updated successfully!"));
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("messages", List.of("Failed to update profile. Please try again later."));
                request.setAttribute("messageType", "error");
            }
        } else {
            request.setAttribute("messages", errorMessages);
            request.setAttribute("messageType", "error");
        }

        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
	}
}
