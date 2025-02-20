package com.megacitycab.service;

import com.megacitycab.model.User;
import com.megacitycab.dao.UserDAOImpl;

public class UserService {
    
    private UserDAOImpl userDAO;
    
    public UserService() {
        this.userDAO = new UserDAOImpl();
    }
    
    public boolean registerUser(User user) {
        return userDAO.save(user);
    }
    
    public boolean loginAttempt(String username, String password) {
    	return userDAO.credentialValidation(username, password);
    }
    
    public User getUserByUsername(String username) {
        return userDAO.getModelByUsername(username);
    }

    public boolean isUsernameTaken(String username) {
    	return userDAO.isUsernameTaken(username);
    }
    
    public boolean isUsernameTaken(String username, int userId) {
        return userDAO.isUsernameTaken(username, userId);
    }

    public boolean updateUserProfile(User user) {
        return userDAO.updateProfile(user);
    }
    
    public boolean updateUserPassword(User user) {
    	return userDAO.updatePassword(user);
    }
}
