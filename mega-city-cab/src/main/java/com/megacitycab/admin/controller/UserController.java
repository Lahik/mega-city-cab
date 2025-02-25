package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.model.User;
import com.megacitycab.model.Vehicle;
import com.megacitycab.service.UserServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/admin/users")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserServiceImpl userService = new UserServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = userService.getAllUsers();
		if (users == null){
		    users = new ArrayList<>();
		}
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/views/admin/users.jsp").forward(request, response);
	}
}
