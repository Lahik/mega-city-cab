package com.megacitycab.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.megacitycab.model.Admin;

public class AdminSessionUtils {

    public static Admin getLoggedInAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Admin) session.getAttribute("admin");
        }
        return null;
    }
    
    public static void setLoggedInAdminInSession(HttpServletRequest request, Admin loggedInAdmin) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("admin", loggedInAdmin);
        }
    }
    
    public static boolean isSuperAdmin(HttpServletRequest request) {
        Admin loggedInAdmin = getLoggedInAdmin(request);
        return loggedInAdmin != null && loggedInAdmin.getId() == 1;
    }
    
    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
