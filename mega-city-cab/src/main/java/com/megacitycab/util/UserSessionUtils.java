package com.megacitycab.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.megacitycab.model.User;

public class UserSessionUtils {

    public static User getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("user");
        }
        return null;
    }
    
    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
    
    public static void setLoggedInUserInSession(HttpServletRequest request, User loggedInUser) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("user", loggedInUser);
        }
    }
}
