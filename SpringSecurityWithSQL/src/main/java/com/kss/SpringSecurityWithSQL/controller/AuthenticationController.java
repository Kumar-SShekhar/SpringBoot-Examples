package com.kss.SpringSecurityWithSQL.controller;

import com.kss.SpringSecurityWithSQL.dto.LoginDto;
import com.kss.SpringSecurityWithSQL.dto.SignUpDto;

import com.kss.SpringSecurityWithSQL.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(authenticationService.registerUser(signUpDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        return new ResponseEntity<>(authenticationService.loginUser(loginDto) , HttpStatus.OK);

    }
}
