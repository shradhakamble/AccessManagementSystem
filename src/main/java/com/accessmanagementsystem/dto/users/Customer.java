package com.accessmanagementsystem.dto.users;

import com.accessmanagementsystem.dto.VerificationResult;
import com.accessmanagementsystem.handler.PhoneVerificationHandler;
import com.accessmanagementsystem.repository.entity.DBUsers;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public class Customer extends DBUsers implements ApplicationUser {

    private AuthenticationManager authenticationManager;
    private PhoneVerificationHandler phoneVerificationHandler;

    public Customer(DBUsers user) {
        super(user);
    }

    @Override
    public Boolean isMultipleSessionsAllowed() {
        return true;
    }

    @Override
    public Boolean isOTPLoginAllowed() {
        return true;
    }

    @Override
    public VerificationResult processOTPLogin(String username, String password) {
        // TODO: Add multiple sessions
        if (password.isEmpty()) {
            return phoneVerificationHandler.startVerification(username);
        } else {
            return phoneVerificationHandler.checkVerification(username, password);
        }
    }

    @Override
    public VerificationResult processUsernameAndPassword(String username, String password) throws Exception {
        // TODO: Add multiple sessions
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        if (authenticate.isAuthenticated()) {
            return new VerificationResult(UUID.randomUUID().toString());
        } else {
            throw new Exception("Invalid Credentials");
        }
    }

    public Customer setAuthenticationManager(AuthenticationManager manager) {
        this.authenticationManager = manager;
        return this;
    }

    public Customer setPhoneVerificationHandler(PhoneVerificationHandler handler) {
        this.phoneVerificationHandler = handler;
        return this;
    }
}
