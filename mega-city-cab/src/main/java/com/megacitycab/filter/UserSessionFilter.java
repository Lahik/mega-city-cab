package com.megacitycab.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megacitycab.util.CSRFTokenUtil;

@WebFilter(urlPatterns = "/*")
public class UserSessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        // checks the url path is related to admin
        if (req.getRequestURI().startsWith("/admin")) {
            chain.doFilter(request, response);
            return;
        }
        
        String requestURI = req.getServletPath();
        
        if (req.getMethod().equalsIgnoreCase("POST") && !CSRFTokenUtil.isValid(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF Token Invalid");
            return;
        }
        
        HttpSession session = req.getSession(false);
        boolean isUserLoggedIn = (session != null && session.getAttribute("user") != null);
        
        if ((requestURI.equals("/login") || requestURI.equals("/register")) && isUserLoggedIn) {
        	resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        
        if ((requestURI.equals("/booking") || 
             requestURI.equals("/profile") || 
             requestURI.equals("/my-bookings") || 
             requestURI.equals("/update-password") || 
             requestURI.equals("/logout")) && !isUserLoggedIn) {
        	resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
