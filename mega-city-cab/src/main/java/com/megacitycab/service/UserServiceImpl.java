package com.megacitycab.service;

import java.util.List;

import com.megacitycab.dao.UserDAOImpl;
import com.megacitycab.model.User;

public class UserServiceImpl implements UserService{
    
    private UserDAOImpl userDAO;
    
    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }
    
    @Override
    public boolean createUser(User user) {
        return userDAO.insertUser(user);
    }
    
    @Override
    public boolean authenticateUser(String username, String password) {
    	return userDAO.validateCredentials(username, password);
    }
    
    @Override
    public User getUserDetails(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public boolean isUsernameTaken(String username) {
    	return userDAO.isUsernameTaken(username);
    }
    
    @Override
    public boolean isUsernameTaken(String username, int userId) {
        return userDAO.isUsernameTaken(username, userId);
    }

    @Override
    public boolean updateProfileInfo(User user) {
        return userDAO.updateUserDetails(user);
    }
    
    @Override
    public boolean resetPassword(User user) {
    	return userDAO.changeUserPassword(user);
    }

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public void deleteUser(int id) {
		userDAO.deleteUser(id);
	}

	@Override
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}
}
