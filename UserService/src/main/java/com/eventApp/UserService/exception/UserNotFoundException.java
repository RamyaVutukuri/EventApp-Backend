package com.eventApp.UserService.exception;
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}