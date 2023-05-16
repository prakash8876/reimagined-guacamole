package io.matoshri.demo.repo;

import io.matoshri.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(@Param("email") String email);
    Optional<User> findByUsernameAndPassword(@Param("username") String username,
                                             @Param("password") String password);

    boolean existsByEmail(String email);
}
