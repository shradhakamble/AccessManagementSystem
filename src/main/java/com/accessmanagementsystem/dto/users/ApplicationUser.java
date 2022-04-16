package com.accessmanagementsystem.dto.users;

import com.accessmanagementsystem.dto.VerificationResult;
import com.accessmanagementsystem.util.PhoneUtil;

public interface ApplicationUser {
    Boolean isMultipleSessionsAllowed();
    Boolean isOTPLoginAllowed();
    VerificationResult processOTPLogin(String username, String password) throws Exception;
    VerificationResult processUsernameAndPassword(String username, String password) throws Exception;

    default VerificationResult validCredentials(String username,String password) throws Exception {
        if(PhoneUtil.checkIfPhoneNumber(username)){
            return processOTPLogin(username, password);
        }
        return processUsernameAndPassword(username,password);
    }
}
