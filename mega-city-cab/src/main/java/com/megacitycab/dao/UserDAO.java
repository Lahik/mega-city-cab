package com.megacitycab.dao;

import java.util.List;

import com.megacitycab.model.User;

public interface UserDAO extends AccountDAO<User>{
	boolean isUsernameTaken(String username);
	boolean updateUserDetails(User entity);
	boolean changeUserPassword(User entity);
	List<User> getAllUsers();
	void deleteUser(int id);
}
