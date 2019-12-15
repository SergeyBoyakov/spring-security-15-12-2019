package com.medium.springsecurity15122019.service;

import com.medium.springsecurity15122019.domain.User;
import com.medium.springsecurity15122019.repository.UserRepository;
import com.medium.springsecurity15122019.security.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with name " + username + " not found"));
    }
}
