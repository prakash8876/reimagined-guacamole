package io.matoshri.demo.service;

import io.matoshri.demo.dto.UserDTO;
import io.matoshri.demo.entity.User;
import io.matoshri.demo.exception.UserExistsException;
import io.matoshri.demo.exception.UserNotFoundException;
import io.matoshri.demo.repo.UserRepository;
import io.matoshri.demo.util.AppConstants;
import io.matoshri.demo.util.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final AppUtils appUtils;

    @Override
    public UserDTO findByEmail(String email) {
        log.info(AppConstants.FIND_USER + email);
        User user = fetchByEmail(email);
        return appUtils.mapToDTO(user);
    }

    @Override
    public UserDTO findByUsernameAndPassword(String username, String password) {
        log.info(AppConstants.FIND_USER + username);
        User user = fetchByUsernameAndPassword(username, password);
        return appUtils.mapToDTO(user);
    }

    @Override
    public List<UserDTO> findUsers() {
        List<User> users = userRepository.findAll();
        log.info("Fetched users: {}", users.size());
        return users.stream()
                .map(user -> appUtils.mapToDTO(user))
                .toList();
    }

    @Transactional
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserExistsException(AppConstants.USER_ALREADY_EXISTS + userDTO.getEmail());
        }
        User user = appUtils.mapToUser(userDTO);
        userRepository.save(user);
        log.info("User saved: {}", user.getEmail());
        return userDTO;
    }

    @Transactional
    @Override
    public String saveUsers(List<UserDTO> userDTOS) {
        List<User> users = new ArrayList<>();
        userDTOS.forEach(userDTO -> {
            String email = userDTO.getEmail();
            if (userRepository.existsByEmail(email)) {
                throw new UserExistsException(AppConstants.USER_ALREADY_EXISTS + email);
            }
            users.add(appUtils.mapToUser(userDTO));
        });
        List<User> saveAll = userRepository.saveAll(users);
        log.info("Saved users: {}", saveAll.size());
        return AppConstants.SAVED_USERS + saveAll.size();
    }

    @Transactional
    @Override
    public UserDTO updateUserByEmail(String email, UserDTO userDTO) {
        if (userRepository.existsByEmail(email)) {
            User user = fetchByEmail(email);
            user.setAge(userDTO.getAge());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setMobileNo(userDTO.getMobileNo());
            userRepository.save(user);
        } else {
            log.error(AppConstants.USER_NOT_FOUND + email);
            throw new UserNotFoundException(AppConstants.USER_NOT_FOUND + email);
        }
        log.info("Updated user: {}", email);
        return userDTO;
    }

    @Override
    public void deleteByEmail(String email) {
        User user = fetchByEmail(email);
        userRepository.delete(user);
        log.info("User deleted: {}", email);
    }

    private User fetchByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error(AppConstants.USER_NOT_FOUND + email);
                    throw new UserNotFoundException(AppConstants.USER_NOT_FOUND + email);
                });
    }

    private User fetchByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> {
                    log.error(AppConstants.USER_NOT_FOUND + username);
                    throw new UserNotFoundException(AppConstants.USER_NOT_FOUND + username);
                });
    }
}
