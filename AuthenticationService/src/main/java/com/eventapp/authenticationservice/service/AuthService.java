package com.eventapp.authenticationservice.service;

import com.eventapp.authenticationservice.exception.UserNotFoundException;
import com.eventapp.authenticationservice.model.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserInfo saveUserDetails(UserInfo user);

    boolean login(String username,String password) throws UserNotFoundException;

}
