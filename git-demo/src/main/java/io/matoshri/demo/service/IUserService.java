package io.matoshri.demo.service;

import io.matoshri.demo.dto.UserDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IUserService {

    //GET
    public CompletableFuture<UserDTO> findByEmail(String email);
    public CompletableFuture<UserDTO> findByUsernameAndPassword(String username, String password);
    public CompletableFuture<List<UserDTO>> findUsers();

    // POST
    public UserDTO saveUser(UserDTO userDTO);
    public String saveUsers(List<UserDTO> userDTOS);

    // PUT
    public UserDTO updateUserByEmail(String email, UserDTO userDTO);

    // DELETE
    public void deleteByEmail(String email);

}
