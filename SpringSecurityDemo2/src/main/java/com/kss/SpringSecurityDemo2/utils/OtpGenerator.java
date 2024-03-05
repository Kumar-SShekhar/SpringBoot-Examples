package com.kss.SpringSecurityDemo2.utils;


import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class OtpGenerator {

    public String generateOtp(){
        SecureRandom secureRandom = new SecureRandom();
        int randomNumber = secureRandom.nextInt(900000) + 100000;
        return String.format("%06d", randomNumber);
    }
}
