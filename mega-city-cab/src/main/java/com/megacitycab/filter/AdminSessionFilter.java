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

import com.megacitycab.util.AdminSessionUtils;
import com.megacitycab.util.CSRFTokenUtil;

@WebFilter(urlPatterns = "/admin/*")
public class AdminSessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestURI = req.getServletPath();
        
        if (req.getMethod().equalsIgnoreCase("POST") && !CSRFTokenUtil.isValid(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF Token Invalid");
            return;
        }

        HttpSession session = req.getSession(false);
        boolean isAdminLoggedIn = (session != null && session.getAttribute("admin") != null);
        boolean isSuperAdmin = isAdminLoggedIn && AdminSessionUtils.isSuperAdmin(req);

        if (requestURI.equals("/admin/login") && isAdminLoggedIn) {
            resp.sendRedirect(req.getContextPath() + "/admin");
            return;
        }

        if ((requestURI.startsWith("/admin") && !requestURI.equals("/admin/login")) && !isAdminLoggedIn) {
            resp.sendRedirect(req.getContextPath() + "/admin/login");
            return;
        }

        if ((requestURI.startsWith("/admin/admins") || 
        	 requestURI.startsWith("/admin/fare") || 
        	 requestURI.startsWith("/admin/users")) && !isSuperAdmin) {
            resp.sendRedirect(req.getContextPath() + "/admin");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
