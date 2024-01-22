package com.eventapp.authenticationservice.repository;

import com.eventapp.authenticationservice.exception.UserNotFoundException;
import com.eventapp.authenticationservice.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<UserInfo,String> {
    Optional<UserInfo> findByUsernameAndPassword(String username, String password) throws UserNotFoundException;

}
