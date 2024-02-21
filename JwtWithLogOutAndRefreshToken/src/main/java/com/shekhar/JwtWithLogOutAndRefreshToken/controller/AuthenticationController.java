package com.shekhar.JwtWithLogOutAndRefreshToken.controller;

import com.shekhar.JwtWithLogOutAndRefreshToken.dto.AuthenticationResponse;
import com.shekhar.JwtWithLogOutAndRefreshToken.dto.LoginDto;
import com.shekhar.JwtWithLogOutAndRefreshToken.dto.RegisterDto;
import com.shekhar.JwtWithLogOutAndRefreshToken.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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


}
