package com.megacitycab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.model.User;
import com.megacitycab.service.UserServiceImpl;
import com.megacitycab.service.ValidationService;
import com.megacitycab.util.PasswordHasher;
import com.megacitycab.validation.PasswordValidator;

					/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name").trim();
        String address = request.getParameter("address").trim();
        String nic = request.getParameter("nic").trim();
        String telephone = request.getParameter("telephone").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        
        boolean isValid = true;
        List<String> errorMessages = new ArrayList<String>();

        ValidationService validationService = new ValidationService();
        PasswordValidator passwordValidator = new PasswordValidator(); 
        
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

        if (!passwordValidator.confirmPassword(password, confirmPassword)) {
        	errorMessages.add("Passwords does not match");
            isValid = false;
        }

        if (!validationService.validate("sanitization", name, null) || 
            !validationService.validate("sanitization", address, null)) {
            errorMessages.add(validationService.getErrorMessage("sanitization"));
            isValid = false;
        }

        if (isValid) {
        	String hashedPassword = PasswordHasher.hashPassword(password);

        	UserServiceImpl userService = new UserServiceImpl();
        	if(userService.isUsernameTaken(username)) {
        		request.setAttribute("messages", List.of("Username already taken!"));
            	request.setAttribute("messageType", "error");
            	
            	request.setAttribute("name", name);
            	request.setAttribute("address", address);
            	request.setAttribute("nic", nic);
            	request.setAttribute("telephone", telephone);
            	
            	request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            	return;
        	}
        	
            User user = new User(name, address, nic, telephone, username, hashedPassword);
            
            boolean registrationSuccessful = userService.createUser(user);

            if (registrationSuccessful) {
            	User userWithID = userService.getUserDetails(username);
            	request.getSession().setAttribute("user", userWithID);
            	
                response.sendRedirect(request.getContextPath() + "/");
            } else {
            	request.setAttribute("messages", List.of("Registration failed. Try again Later."));
            	request.setAttribute("messageType", "error");
            	
            	request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            }
        } else {
        	request.setAttribute("name", name);
        	request.setAttribute("username", username);
        	request.setAttribute("address", address);
        	request.setAttribute("nic", nic);
        	request.setAttribute("telephone", telephone);
        	
        	request.setAttribute("messages", errorMessages);
        	request.setAttribute("messageType", "error");
        	
        	request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
	}

}
