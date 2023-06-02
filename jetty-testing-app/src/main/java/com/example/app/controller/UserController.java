package com.example.app.controller;

import com.example.app.entity.User;
import com.example.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/get-by-email")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByEmail(@RequestParam(name = "email") String email) throws Exception
    {
        CompletableFuture<User> future = userService.getUserByEmail(email);
        return future.get();
    }

    @GetMapping("/get-by-username")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsernameAndPassword(@RequestParam(name = "username") String username,
                                             @RequestParam(name = "password") String password) throws Exception
    {
        CompletableFuture<User> future = userService.getUserByUsernameAndPassword(username, password);
        return future.get();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() throws Exception {
        CompletableFuture<List<User>> future = userService.getAllUsers();
        List<User> users = future.get();
        log.info("controller {}", users.size());
        return users;
    }
}
