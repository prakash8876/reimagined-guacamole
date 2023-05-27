package io.matoshri.demo.controller;

import io.matoshri.demo.dto.UserDTO;
import io.matoshri.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private @Autowired IUserService userService;

    //GET
    @GetMapping("/by/{email}")
    public UserDTO findBy(@PathVariable("email") String email)
            throws Exception {

        CompletableFuture<UserDTO> future = userService.findByEmail(email);
        return future.get();
    }

    @GetMapping("/by/{username}/{password}")
    public UserDTO findBy(@PathVariable("username") String username,
                          @PathVariable("password") String password)
    throws Exception {
        return userService.findByUsernameAndPassword(username, password).get();
    }

    @GetMapping
    public List<UserDTO> findUsers()
    throws Exception {
        return userService.findUsers().get();
    }

    // POST
    @PostMapping("/save")
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
    @PutMapping("/update/{email}")
    public UserDTO updateBy(@PathVariable("email") String email,
                                     @RequestBody final UserDTO userDTO) {
        return userService.updateUserByEmail(email, userDTO);
    }

    // DELETE
    @DeleteMapping("/delete/{email}")
    public void deleteByEmail(@PathVariable("email") final String email) {
        userService.deleteByEmail(email);
    }
}
