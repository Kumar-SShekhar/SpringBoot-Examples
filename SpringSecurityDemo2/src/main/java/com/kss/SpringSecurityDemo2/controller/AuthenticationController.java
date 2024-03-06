package com.kss.SpringSecurityDemo2.controller;

import com.kss.SpringSecurityDemo2.dto.AuthenticationResponse;
import com.kss.SpringSecurityDemo2.dto.ForgotPasswordVerificationDto;
import com.kss.SpringSecurityDemo2.dto.LoginDto;
import com.kss.SpringSecurityDemo2.dto.RegisterDto;
import com.kss.SpringSecurityDemo2.service.AuthenticationService;
import jakarta.servlet.SessionTrackingMode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authenticationService.loginUser(loginDto), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(authenticationService.registerUser(registerDto), HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(HttpServletRequest request, HttpServletResponse response){
        return new ResponseEntity<>(authenticationService.refreshToken(request, response), HttpStatus.OK);
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String,String> emailRequest){
        return new ResponseEntity<>(authenticationService.forgotPassword(emailRequest.get("email")), HttpStatus.OK);
    }

    @PostMapping("/verify-forgot-password")
    public ResponseEntity<String> verifyForgotPassword(@RequestBody ForgotPasswordVerificationDto forgotPasswordVerificationDto){
        return new ResponseEntity<>(authenticationService.verifyForgotPassword(forgotPasswordVerificationDto),HttpStatus.OK);
    }


}
