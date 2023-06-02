package com.example.app.repo;

import com.example.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
                extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameAndPassword(String username, String password);

    Boolean existsByEmail(String email);
    Boolean existsByUsernameAndPassword(String username, String password);
}
