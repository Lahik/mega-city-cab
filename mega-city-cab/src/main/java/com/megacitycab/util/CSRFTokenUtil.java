package com.megacitycab.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Base64;

public class CSRFTokenUtil {

    private static final String CSRF_TOKEN = "csrf_token";

    public static void setToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(CSRF_TOKEN) == null) {
            session.setAttribute(CSRF_TOKEN, generateToken());
        }
    }

    public static String getToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(CSRF_TOKEN);
    }

    public static boolean isValid(HttpServletRequest request) {
        String sessionToken = getToken(request);
        String requestToken = request.getParameter(CSRF_TOKEN);
        return sessionToken != null && sessionToken.equals(requestToken);
    }
    
    private static String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
