package com.accessmanagementsystem.controller;

import com.accessmanagementsystem.dto.AuthRequest;
import com.accessmanagementsystem.dto.VerificationResult;
import com.accessmanagementsystem.dto.users.ApplicationUser;
import com.accessmanagementsystem.dto.users.UserFactory;
import com.accessmanagementsystem.repository.UserRepository;
import com.accessmanagementsystem.repository.entity.DBUsers;
import com.accessmanagementsystem.util.IJwtGenerator;
import com.accessmanagementsystem.util.JwtUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserFactory factory;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            // fail fast --> if username is not provided send an invalid response
            if (authRequest.getUserName().isEmpty()) {
                throw new Exception("Invalid Username");
            }

            // check if valid user request
            DBUsers user = repository.findByUserNameOrPhone(authRequest.getUserName());
            if (user == null) {
                throw new Exception("User not found. Check your username.");
            }

            // Authenticate user either via OTP or via Username and Password
            ApplicationUser appUser = factory.getUserType(user);
            VerificationResult auth = appUser.validCredentials(authRequest.getUserName(), authRequest.getPassword());
            if (!auth.isValid()) {
                return auth.getErrors()[0];
            }

            // interface to handle multiple signature algorithms in the future
            IJwtGenerator generator = jwtUtil.getGenerator(SignatureAlgorithm.HS256);
            return generator.generateToken(authRequest.getUserName());
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
