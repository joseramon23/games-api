package com.example.authservice.services;

import com.example.authservice.common.dtos.TokenResponse;
import io.jsonwebtoken.Claims;

public interface JwtService {
    TokenResponse generateToken(Long userId);
    Claims getClaims(String token);
    boolean isExpired(String token);
    Integer extractUserId(String id);
}
