package com.shekhar.RolesAndPermissionsDemo.service;

import com.shekhar.RolesAndPermissionsDemo.config.JwtService;
import com.shekhar.RolesAndPermissionsDemo.dto.AuthenticationResponse;
import com.shekhar.RolesAndPermissionsDemo.dto.LoginDto;
import com.shekhar.RolesAndPermissionsDemo.dto.RegisterDto;
import com.shekhar.RolesAndPermissionsDemo.model.Role;
import com.shekhar.RolesAndPermissionsDemo.model.User;
import com.shekhar.RolesAndPermissionsDemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterDto registerDto){

        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

//        User user = new User();
//        user.setName(registerDto.getName());
//        user.setEmail(registerDto.getEmail());
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//        user.setRole(Role.USER);
//
//        if(userRepository.findByEmail(registerDto.getEmail()).isPresent()){
//            return "User already exists";
//        }
//
//        userRepository.save(user);
//
//        return "User Registered Successfully";
    }

    public AuthenticationResponse login(LoginDto loginDto){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
//
////        if(authentication == null){
////            return "Invalid credentials";
////        }
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return "User Login Successful";
    }
}
