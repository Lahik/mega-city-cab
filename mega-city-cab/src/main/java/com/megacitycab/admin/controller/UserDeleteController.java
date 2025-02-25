package com.megacitycab.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megacitycab.model.User;
import com.megacitycab.service.UserServiceImpl;

/**
 * Servlet implementation class UserDeleteController
 */
@WebServlet("/admin/users/delete")
public class UserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserServiceImpl userService = new UserServiceImpl(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

        if (id == null || id.isEmpty()) {
            setErrorAndForward(request, response, "User ID is missing.");
            return;
        }
		
        userService.deleteUser(Integer.parseInt(id));
        request.setAttribute("messages", List.of("User deleted successfully!"));
        request.setAttribute("messageType", "success");
        
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users); 
        request.getRequestDispatcher("/WEB-INF/views/admin/users.jsp").forward(request, response);
	}

	private void setErrorAndForward(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("messages", List.of(errorMessage));
        request.setAttribute("messageType", "error");
        
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users); 
        request.getRequestDispatcher("/WEB-INF/views/admin/users.jsp").forward(request, response);
    }
}
