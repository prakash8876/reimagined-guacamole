package io.matoshri.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
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
    private String email;
}
