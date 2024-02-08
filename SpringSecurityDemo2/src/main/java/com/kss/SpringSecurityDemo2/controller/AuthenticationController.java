package com.kss.SpringSecurityDemo2.controller;

import com.kss.SpringSecurityDemo2.dto.AuthenticationResponse;
import com.kss.SpringSecurityDemo2.dto.LoginDto;
import com.kss.SpringSecurityDemo2.dto.RegisterDto;
import com.kss.SpringSecurityDemo2.service.AuthenticationService;
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
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(authenticationService.loginUser(loginDto) , HttpStatus.OK);
    }


//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("Login successful" , HttpStatus.OK);
//
//    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(authenticationService.registerUser(registerDto), HttpStatus.CREATED);
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
}
