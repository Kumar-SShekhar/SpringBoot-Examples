package com.kss.SpringSecurityDemo2.dto;

import lombok.Getter;

@Getter
public class ForgotPasswordVerificationDto {
    private String email;
    private String otp;
    private String newPassword;
    private String confirmPassword;
}
