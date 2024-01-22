package com.eventapp.authenticationservice.service;

import com.eventapp.authenticationservice.exception.UserNotFoundException;
import com.eventapp.authenticationservice.model.UserInfo;
import com.eventapp.authenticationservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserInfo saveUserDetails(UserInfo user) {
        return	userRepo.save(user);
    }

    @Override
    public boolean login(String username, String password) throws UserNotFoundException {
        Optional<UserInfo> user= userRepo.findByUsernameAndPassword(username, password);
        if(user.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
