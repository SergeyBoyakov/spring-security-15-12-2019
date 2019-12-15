package com.medium.springsecurity15122019.controller;

import com.google.common.collect.ImmutableMap;
import com.medium.springsecurity15122019.security.JwtTokenProvider;
import com.medium.springsecurity15122019.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthtorizationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest authenticationRequest) {
        // todo refactor this peace of code
        try {
            String username = authenticationRequest.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequest.getPassword()));

            String token = jwtTokenProvider.createToken(username, userService.findByUserName(username).getRoles());

            return ResponseEntity.ok(
                    ImmutableMap.of(
                            "username", username,
                            "token", token));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username / password supplied");
        }
    }
}
