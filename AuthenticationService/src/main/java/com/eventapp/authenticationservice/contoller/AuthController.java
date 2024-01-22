package com.eventapp.authenticationservice.contoller;

import com.eventapp.authenticationservice.config.JwtTokenGenerator;
import com.eventapp.authenticationservice.exception.UserNotFoundException;
import com.eventapp.authenticationservice.model.UserInfo;
import com.eventapp.authenticationservice.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserInfo user) throws UserNotFoundException {

        boolean result=authService.login(user.getUsername(), user.getPassword());

        if (user.getUsername() == null || user.getPassword() == null) {
            logger.error("User and password can't be null");
            throw new UserNotFoundException("Username and password can't be null");
        }

        if(result) {
            Map<String, String> token= new JwtTokenGenerator().generateToken(user);
            logger.info("Token generated successfully");
            return new ResponseEntity<Map>(token,HttpStatus.OK);
        }
        else{
            logger.error("Invalid username and password");
            return new ResponseEntity("Invalid username and password",HttpStatus.UNAUTHORIZED);
        }
    }
}
