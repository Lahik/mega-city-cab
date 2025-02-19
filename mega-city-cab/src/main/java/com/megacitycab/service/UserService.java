package com.megacitycab.service;

import com.megacitycab.model.User;
import com.megacitycab.dao.UserDAO;

public class UserService {
    
    private UserDAO userDAO;
    
    public UserService() {
        this.userDAO = new UserDAO();
    }
    
    public boolean registerUser(User user) {
        return userDAO.save(user);
    }
    
    public boolean loginAttempt(String username, String password) {
    	return userDAO.credentialValidation(username, password);
    }
    
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public boolean isUsernameTaken(String username) {
    	return userDAO.isUsernameTaken(username);
    }
}
