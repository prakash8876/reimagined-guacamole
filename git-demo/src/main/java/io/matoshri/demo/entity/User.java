package io.matoshri.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_tbl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "user_id", unique = true, updatable = false)
    private Integer id;

    private String firstName;
    private String lastName;
    @Min(20)
    @Max(60)
    private int age;
    @Size(max = 10)
    private String mobileNo;
    private String username;
    private String password;
    @Email(message = "Enter proper email address")
    @Column(unique = true)
    private String email;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
