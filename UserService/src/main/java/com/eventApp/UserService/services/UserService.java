package com.eventApp.UserService.services;

import com.eventApp.UserService.exception.UserAlreadyExistsException;
import com.eventApp.UserService.exception.UserNotFoundException;
import com.eventApp.UserService.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user) throws UserAlreadyExistsException;

    List<User> getAllUsers();

    User getUserByUsername(String username) throws UserNotFoundException;

    User updateUser(User user) throws UserNotFoundException;

}
