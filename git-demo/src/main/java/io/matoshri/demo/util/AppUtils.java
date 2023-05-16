package io.matoshri.demo.util;

import io.matoshri.demo.dto.UserDTO;
import io.matoshri.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {

    public User mapToUser(UserDTO userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .age(userDTO.getAge())
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .mobileNo(userDTO.getMobileNo())
                .build();
    }

    public UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .mobileNo(user.getMobileNo())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
