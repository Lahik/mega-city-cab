package com.megacitycab.dao;

import com.megacitycab.model.User;

public interface UserDAO extends AccountDAO<User>{
	boolean isUsernameTaken(String username);
}
