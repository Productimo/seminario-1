package com.seminario.services;

import com.seminario.dtos.UserDTO;
import com.seminario.entitys.User;
import com.seminario.exceptions.UserAlreadyExistException;

import java.net.URI;

public interface UserService {

    User registerUser(UserDTO userDTO) throws UserAlreadyExistException;
    URI buildSingUpUserUriCreated();
    User getUserByUsername(String username);
    User getUserByEmail(String username);
    boolean isUserRegistered(String username);
    boolean isEmailRegistered(String email);

}