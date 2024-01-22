package com.eventApp.userservice.test.service;

import com.eventApp.UserService.exception.UserNotFoundException;
import com.eventApp.UserService.model.User;
import com.eventApp.UserService.repository.UserRepository;
import com.eventApp.UserService.services.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    private List<User> userList;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User(1, "user1", "user1@123", "user1@gmail.com","7013781336");
        optional = Optional.of(user);
    }

    @AfterEach
    public void tearDown() {
        user = null;
    }

    @Test
    public void givenUserToSaveThenShouldReturnSavedUser() {
        when(userRepository.save(any())).thenReturn(user);
        assertEquals(user, userRepository.save(user));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void givenGetAllUsersThenShouldReturnListOfAllUsers() {
        userRepository.save(user);
        //stubbing the mock to return specific data
        when(userRepository.findAll()).thenReturn(userList);
        List<User> userList1 = userService.getAllUsers();
        assertEquals(userList, userList1);
        verify(userRepository, times(1)).save(user);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void givenUserUsernameThenShouldReturnRespectiveUser() throws UserNotFoundException {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        User retrievedUser = userService.getUserByUsername(user.getUsername());
        verify(userRepository, times(1)).findByUsername(anyString());

    }

    @Test
    public void givenUserToUpdateThenShouldReturnUpdatedUser() throws UserNotFoundException {
        when(userRepository.findById(user.getUserId())).thenReturn(optional);
        when(userRepository.save(user)).thenReturn(user);
        user.setUsername("ramya");
        User user1 = userService.updateUser(user);
        assertEquals(user1.getUsername(), "ramya");
        verify(userRepository, times(1)).save(user);
        verify(userRepository, atMost(2)).findById(user.getUserId());
    }

    @Test
    void givenUserToUpdateThenShouldNotReturnUpdatedUser() throws UserNotFoundException {
        User userToUpdate = new User();
        userToUpdate.setUserId(2);
        when(userRepository.findById(userToUpdate.getUserId())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, ()-> userService.updateUser(userToUpdate));

    }
}
