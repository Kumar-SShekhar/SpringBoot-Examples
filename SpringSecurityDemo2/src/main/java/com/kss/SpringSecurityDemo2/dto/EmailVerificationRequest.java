package com.kss.SpringSecurityDemo2.dto;

import lombok.Getter;

@Getter
public class EmailVerificationRequest {

    private String email;
    private String otp;
}
