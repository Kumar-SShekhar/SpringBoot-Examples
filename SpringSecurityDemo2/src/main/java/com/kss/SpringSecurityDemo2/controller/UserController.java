package com.kss.SpringSecurityDemo2.controller;


import com.kss.SpringSecurityDemo2.dto.ChangePasswordDto;
import com.kss.SpringSecurityDemo2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;


@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PreAuthorize("hasRole('ADMIN)")
    @PreAuthorize("hasRole(T(com.kss.SpringSecurityDemo2.entity.Role).ADMIN)")
    @GetMapping("/message")
    public ResponseEntity<String> getMessage(){
        String message = "Hello from secured endpoint";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto, HttpServletRequest request){
        String message = userService.changePassword(changePasswordDto, request);
        return ResponseEntity.status(200).body(message);
    }

    @PostMapping("/sendmail/{email}")
    public ResponseEntity<String> sendMail(@PathVariable String email){
        return new ResponseEntity<>(userService.sendEmail(email), HttpStatus.OK);
    }

    @PostMapping("/sendotp/{email}")
    public ResponseEntity<String> sendEmailOtp(@PathVariable String email){
        return new ResponseEntity<>(userService.sendEmailOtp(email), HttpStatus.OK);
    }

}
