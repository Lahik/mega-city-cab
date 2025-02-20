package com.megacitycab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.model.User;
import com.megacitycab.service.UserServiceImpl;
import com.megacitycab.util.PasswordHasher;
import com.megacitycab.validation.UsernameValidator;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username").trim();
	    String password = request.getParameter("password");

	    UserServiceImpl userService = new UserServiceImpl();
	    UsernameValidator usernameValidator = new UsernameValidator();
	    
	    if(!usernameValidator.validate(username)) {
	    	request.setAttribute("messages", List.of(usernameValidator.getErrorMessage()));
        	request.setAttribute("messageType", "error");

	        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	        return;
	    }
	    
	    if(userService.authenticateUser(username, password)) {
	    	User user = userService.getUserDetails(username);
	        request.getSession().setAttribute("user", user);

	        response.sendRedirect(request.getContextPath() + "/");
	    } else {
	        request.setAttribute("username", username);
	        request.setAttribute("messages", List.of("Invalid Credentials!"));
        	request.setAttribute("messageType", "error");

	        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	    }
	}
}
