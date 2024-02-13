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
//        if(userRepository.existsByEmail(signUpDto.getEmail()) || userRepository.existsByUserName(signUpDto.getUserName())){
//            return new ResponseEntity<>("User already exists" , HttpStatus.BAD_REQUEST);
//        }
//
//        User user = new User();
//
//        user.setName(signUpDto.getName());
//        user.setUserName(signUpDto.getUserName());
//        user.setEmail(signUpDto.getEmail());
//        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
//
//        Role roles=roleRepository.findByName("USER").get();
//        user.setRoles(Collections.singletonList(roles));
//        userRepository.save(user);
//
//        return new ResponseEntity<>("User registered successfully" , HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        return new ResponseEntity<>(authenticationService.loginUser(loginDto) , HttpStatus.OK);

    }
}
