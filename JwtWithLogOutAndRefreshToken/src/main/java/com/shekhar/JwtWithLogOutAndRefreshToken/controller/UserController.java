package com.shekhar.JwtWithLogOutAndRefreshToken.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/message")
    public ResponseEntity<String> getMessage(){
        String message = "Hello from secured endpoint";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
