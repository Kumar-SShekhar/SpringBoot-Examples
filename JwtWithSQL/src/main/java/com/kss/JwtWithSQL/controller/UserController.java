package com.kss.JwtWithSQL.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/message")
    public ResponseEntity<String> getMessage(){
        String message = "Hello from secured endpoint";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }



//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto){
//        if(userRepository.existsByEmail(registerDto.getEmail()) || userRepository.existsByUserName(registerDto.getUserName())){
//            return new ResponseEntity<>("User already exists" , HttpStatus.BAD_REQUEST);
//        }
//
//        User user = new User();
//
//        user.setName(registerDto.getName());
//        user.setUserName(registerDto.getUserName());
//        user.setEmail(registerDto.getEmail());
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//
//        Role roles=roleRepository.findByName("USER").get();
//        user.setRoles(Collections.singletonList(roles));
//        userRepository.save(user);
//
//        return new ResponseEntity<>("User registered successfully" , HttpStatus.CREATED);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("Login successful" , HttpStatus.OK);
//
//    }

}
