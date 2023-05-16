package io.matoshri.demo.service;

import io.matoshri.demo.dto.UserDTO;

import java.util.List;

public interface IUserService {

    //GET
    public UserDTO findByEmail(String email);
    public UserDTO findByUsernameAndPassword(String username, String password);
    public List<UserDTO> findUsers();

    // POST
    public UserDTO saveUser(UserDTO userDTO);
    public String saveUsers(List<UserDTO> userDTOS);

    // PUT
    public UserDTO updateUserByEmail(String email, UserDTO userDTO);

    // DELETE
    public void deleteByEmail(String email);

}
