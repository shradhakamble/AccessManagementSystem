package com.accessmanagementsystem.dto.users;

import com.accessmanagementsystem.dto.VerificationResult;
import com.accessmanagementsystem.repository.entity.DBUsers;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public class ServiceUser extends DBUsers implements ApplicationUser{

    private AuthenticationManager authenticationManager;

    public ServiceUser(DBUsers user) {
        super(user);
    }

    @Override
    public Boolean isMultipleSessionsAllowed() {
        return false;
    }

    @Override
    public Boolean isOTPLoginAllowed() {
        return false;
    }

    @Override
    public VerificationResult processOTPLogin(String username, String password) throws Exception {
        throw new Exception("OTP Login not allowed for this user");
    }

    @Override
    public VerificationResult processUsernameAndPassword(String username, String password) throws Exception {
        // TODO: Check for multiple sessions
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        if (authenticate.isAuthenticated()) {
            return new VerificationResult(UUID.randomUUID().toString());
        } else {
            throw new Exception("Invalid Credentials");
        }
    }

    public ServiceUser setAuthenticationManager(AuthenticationManager manager) {
        this.authenticationManager = manager;
        return this;
    }
}
