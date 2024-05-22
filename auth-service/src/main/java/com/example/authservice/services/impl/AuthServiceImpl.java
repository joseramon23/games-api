package com.example.authservice.services.impl;

import com.example.authservice.common.dtos.TokenResponse;
import com.example.authservice.common.dtos.UserRequest;
import com.example.authservice.common.entities.UserModel;
import com.example.authservice.services.JwtService;
import com.example.authservice.repositories.UserRepository;
import com.example.authservice.services.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public TokenResponse createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getId()))
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }

    @Override
    public TokenResponse loginUser(UserRequest userRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getEmail());

        return userDetails != null && passwordEncoder.matches(userRequest.getPassword(), userDetails.getPassword())
                ? jwtService.generateToken(((UserModel) userDetails).getId())
                : null;
    }

    private UserModel mapToEntity(UserRequest userRequest) {
        return UserModel.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role("USER")
                .build();
    }
}
