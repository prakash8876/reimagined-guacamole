package io.matoshri.demo.controller;

import io.matoshri.demo.dto.UserDTO;
import io.matoshri.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    //GET
    @GetMapping("/email/{email}")
    public UserDTO findByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/username/{username}/password/{password}")
    public UserDTO findByUsernameAndPassword(@PathVariable("username") String username,
                                             @PathVariable("password") String password) {
        return userService.findByUsernameAndPassword(username, password);
    }

    @GetMapping
    public List<UserDTO> findUsers() {
        return userService.findUsers();
    }

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveUser(@RequestBody final UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PostMapping("/save-all")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUsers(@RequestBody final List<UserDTO> userDTOS) {
        return userService.saveUsers(userDTOS);
    }

    // PUT
    @PutMapping("/email/{email}")
    public UserDTO updateUserByEmail(@PathVariable("email") String email,
                                     @RequestBody final UserDTO userDTO) {
        return userService.updateUserByEmail(email, userDTO);
    }

    // DELETE
    @DeleteMapping("/{email}")
    public void deleteByEmail(@PathVariable("email") final String email) {
        userService.deleteByEmail(email);
    }
}
