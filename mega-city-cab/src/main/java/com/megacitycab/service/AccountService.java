package com.megacitycab.service;

public interface AccountService<T> {
    boolean createUser(T entity);
    boolean authenticateUser(String username, String password);
    T getUserDetails(String username);
    boolean isUsernameTaken(String username, int id);
}
