package com.medium.springsecurity15122019.controller;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserInfoController {
    @GetMapping("/me")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(authority -> ((GrantedAuthority) authority).getAuthority())
                .collect(Collectors.toList());

        ImmutableMap<Object, Object> model = ImmutableMap.of(
                "username", userDetails.getUsername(),
                "roles", authorities
        );

        return ResponseEntity.ok(model);
    }
}
