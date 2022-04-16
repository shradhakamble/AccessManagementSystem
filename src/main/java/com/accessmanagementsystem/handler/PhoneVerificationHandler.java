package com.accessmanagementsystem.handler;

import com.accessmanagementsystem.dto.VerificationResult;
import com.accessmanagementsystem.twilio.TwilioProperties;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhoneVerificationHandler {

    private final TwilioProperties twilioproperties;

    @Autowired
    public PhoneVerificationHandler(TwilioProperties twilioproperties) {
        this.twilioproperties = twilioproperties;
    }

    //method to send to otp
    public VerificationResult startVerification(String phone) {
        try {
            Verification verification = Verification.creator(twilioproperties.getServiceId(), phone, "sms").create();
            if ("approved".equals(verification.getStatus()) || "pending".equals(verification.getStatus())) {
                return new VerificationResult(
                        new String[]{String.format(
                                "Otp Sent to %s. Please call the API again with the OTP in the password field",
                                phone
                        )}
                );
            }
        } catch (ApiException exception) {
            return new VerificationResult(new String[]{exception.getMessage()});
        }
        return null;
    }

    //method to verify the otp
    public VerificationResult checkVerification(String phone, String code) {
        try {

            // this is just for testing and incase Twilio doesn't send out the OTP
            // should be removed before putting on production
            if (code.equals("12345")) {
                VerificationResult verificationResult = new VerificationResult(UUID.randomUUID().toString());
                verificationResult.setMessage("Otp Validated successfully");
                return verificationResult;
            }

            VerificationCheck verification = VerificationCheck.creator(twilioproperties.getServiceId(), code).setTo(phone).create();
            if ("approved".equals(verification.getStatus())) {
                VerificationResult verificationResult = new VerificationResult(verification.getSid());
                verificationResult.setMessage("Otp Validated successfully");
                return verificationResult;
            }
            return new VerificationResult(new String[]{"Invalid code."});
        } catch (ApiException exception) {
            return new VerificationResult(new String[]{exception.getMessage()});
        }
    }
}
