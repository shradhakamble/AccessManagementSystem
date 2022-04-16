package com.accessmanagementsystem.util;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtGenerator {
    String generateToken(String userName);
    String extractUsername(String token);
    Boolean validateToken(String token, UserDetails userDetails);
}
