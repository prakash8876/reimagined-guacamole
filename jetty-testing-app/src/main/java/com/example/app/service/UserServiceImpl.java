package com.example.app.service;

import com.example.app.entity.User;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl
   implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Async
    public CompletableFuture<User> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email doesn't exists" + email));
        return CompletableFuture.completedFuture(user);
    }

    @Async
    public CompletableFuture<User> getUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new ResourceNotFoundException("Username/password incorrect or doesn't exists" + username));
        return CompletableFuture.completedFuture(user);
    }

    @Async
    public CompletableFuture<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("Fetching records: {}", users.size());
        return CompletableFuture.completedFuture(users);
    }

    @Transactional
    public void createNewUser(User user) {
        User saved = userRepository.save(user);
        log.info("User created {}", saved.getUsername());
    }

    @Transactional
    public void updateExistingUser(User user) {
        if (null == user.getUsername() || null == user.getPassword()) {
            log.error("Username/password not provided");
            return;
        }

        if (null == user.getEmail()) {
            log.error("email not provided");
        }

        User existUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Username/password is incorrect"));
        existUser.setPassword(user.getPassword());
        existUser.setAge(user.getAge());
        existUser.setName(user.getName());
        existUser.setPhone(user.getPhone());
        userRepository.save(existUser);
        log.info("Updated user {}", existUser.getEmail());
    }

    @Transactional
    public void deleteUser(String email) {
        if (userRepository.existsByEmail(email)) {
            User user = userRepository.findByEmail(email).get();
            userRepository.delete(user);
            log.info("Removed email: {}", email);
            return;
        }
        log.error("Email {} doesn't exists for delete", email);
    }

    @Transactional
    public void deleteUser(String username, String password) {
        if (userRepository.existsByUsernameAndPassword(username, password)) {
            User user = userRepository.findByUsernameAndPassword(username, password).get();
            userRepository.delete(user);
            log.info("Removed username: {}", username);
            return;
        }
        log.error("Username {} doesn't exists for delete", username);
    }

    public long count() {
        return userRepository.count();
    }
}
