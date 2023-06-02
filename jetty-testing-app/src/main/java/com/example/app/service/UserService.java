package com.example.app.service;

import com.example.app.entity.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {

    // GET
    public CompletableFuture<User> getUserByEmail(String email);
    public CompletableFuture<User> getUserByUsernameAndPassword(String username, String password);
    public CompletableFuture<List<User>> getAllUsers();

    // POST
    public void createNewUser(User user);

    // PUT
    public void updateExistingUser(User user);

    // DELETE
    public void deleteUser(String email);
    public void deleteUser(String username, String password);

    public long count();
}
