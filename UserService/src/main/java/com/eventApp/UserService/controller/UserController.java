package com.eventApp.UserService.controller;

import com.eventApp.UserService.exception.UserAlreadyExistsException;
import com.eventApp.UserService.exception.UserNotFoundException;
import com.eventApp.UserService.model.User;
import com.eventApp.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws UserAlreadyExistsException {
        logger.info("User registered successfully" + user.toString());
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Retrieved all user details successfully");
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserById(@PathVariable("username") String username) throws UserNotFoundException {
        logger.info("User details retrieved successfully with given id :" + username);
        return new ResponseEntity<>(userService.getUserByUsername(username),HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException {
        logger.info("User details updated successfully" + user.toString());
        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
    }

}
