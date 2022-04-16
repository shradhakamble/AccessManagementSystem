package com.accessmanagementsystem.dto.users;

import com.accessmanagementsystem.handler.PhoneVerificationHandler;
import com.accessmanagementsystem.repository.entity.DBUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PhoneVerificationHandler handler;

    public ApplicationUser getUserType(DBUsers user) throws Exception {
        if(user.getUserType() == UserType.CUSTOMER) {
            return new Customer(user).setAuthenticationManager(manager)
                    .setPhoneVerificationHandler(handler);
        } else if (user.getUserType() == UserType.SERVICE_USER) {
            return new ServiceUser(user).setAuthenticationManager(manager);
        } else {
            throw new Exception("Invalid User.");
        }
    }
}
