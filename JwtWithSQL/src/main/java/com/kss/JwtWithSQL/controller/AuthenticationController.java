package com.kss.JwtWithSQL.controller;

import com.kss.JwtWithSQL.dto.AuthenticationResponse;
import com.kss.JwtWithSQL.dto.LoginDto;
import com.kss.JwtWithSQL.dto.RegisterDto;
import com.kss.JwtWithSQL.service.AuthenticationService;
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


}
