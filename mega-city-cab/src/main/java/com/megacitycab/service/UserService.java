package com.megacitycab.service;

import java.util.List;

import com.megacitycab.model.User;

public interface UserService extends AccountService<User> {
    boolean isUsernameTaken(String username);
    boolean updateProfileInfo(User entity);
    boolean resetPassword(User entity);
    List<User> getAllUsers();
    void deleteUser(int id);
    User getUserById(int id);
}
