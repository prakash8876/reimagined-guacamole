package com.example.app.config;

import com.example.app.entity.User;
import com.example.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Autowired
    private UserService userService;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            if (userService.count() == 0L) {
                User user1 = new User("one",23,"user1","user1","email@email.com","9898989654");
                User user2 = new User("two", 43, "user2","user2","email2@email.com","3434343434");
                userService.createNewUser(user1);
                userService.createNewUser(user2);
            }
        };
    }
}
