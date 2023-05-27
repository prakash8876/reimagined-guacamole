package io.matoshri.demo.dto;

import lombok.*;

import javax.validation.constraints.*;

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
