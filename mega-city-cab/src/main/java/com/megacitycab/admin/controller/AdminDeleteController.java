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

/**
 * Servlet implementation class AdminDeleteController
 */
@WebServlet("/admin/admins/delete")
public class AdminDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminServiceImpl adminService = new AdminServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminIdStr = request.getParameter("id");
        if (adminIdStr == null || adminIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/admins");
            return;
        }

        try {
            int adminId = Integer.parseInt(adminIdStr);

            if (adminId == 1) {
                response.sendRedirect(request.getContextPath() + "/admin/admins");
                return;
            }

            adminService.deleteAdmin(adminId);
            request.setAttribute("messages", List.of("Admin deleted successfully!"));
            request.setAttribute("messageType", "success");
            List<Admin> admins = adminService.getAllAdmins();
    		request.setAttribute("admins", admins);
            
        } catch (NumberFormatException e) {
            request.setAttribute("messages", List.of("Invalid admin ID."));
            request.setAttribute("messageType", "error");
        }
        
        request.getRequestDispatcher("/WEB-INF/views/admin/admins.jsp").forward(request, response);
	}

}
