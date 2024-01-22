package com.eventApp.UserService.services;

import com.eventApp.UserService.exception.UserAlreadyExistsException;
import com.eventApp.UserService.exception.UserNotFoundException;
import com.eventApp.UserService.model.User;
import com.eventApp.UserService.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Gson gson;

    private static final String TOPIC = "topic";


    public User createUser(User user) throws UserAlreadyExistsException {

        Optional<User> username = userRepository.findByUsername(user.getUsername());
        if (username.isPresent()) {
            throw new UserAlreadyExistsException("User already exits with given username");
        }

        userRepository.save(user);
        kafkaTemplate.send(TOPIC, gson.toJson(user));
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        Optional <User> users = userRepository.findByUsername(username);
        if (users.isPresent()) {
            return users.get();
        } else {
            throw new UserNotFoundException("User not found with username : " + username);
        }
    }

    public User updateUser(User user) throws UserNotFoundException {
        Optional<User> users = userRepository.findById(user.getUserId());
        if (users.isPresent()) {
            User userUpdate = users.get();
            userUpdate.setUsername(user.getUsername());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setPhoneNumber(user.getPhoneNumber());
            return userRepository.save(userUpdate);
        }
        else {
            throw new UserNotFoundException("User not found with id : " + user.getUserId());
        }
    }

}
