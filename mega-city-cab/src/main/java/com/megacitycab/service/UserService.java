package com.megacitycab.service;

import com.megacitycab.model.User;

public interface UserService extends AccountService<User> {
    boolean isUsernameTaken(String username);
}
