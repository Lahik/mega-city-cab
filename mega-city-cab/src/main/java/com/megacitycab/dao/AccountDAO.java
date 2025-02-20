package com.megacitycab.dao;

public interface AccountDAO<T> {
    boolean insertUser(T entity);
    boolean validateCredentials(String username, String password);
    T findUserByUsername(String username);
    boolean isUsernameTaken(String username, int id);
    boolean updateUserDetails(T entity);
    boolean changeUserPassword(T entity);
}