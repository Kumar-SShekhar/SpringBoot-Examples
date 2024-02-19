package com.kss.SpringSecurityWithSQL.service;

import com.kss.SpringSecurityWithSQL.dto.LoginDto;
import com.kss.SpringSecurityWithSQL.dto.SignUpDto;
import com.kss.SpringSecurityWithSQL.entity.Role;
import com.kss.SpringSecurityWithSQL.entity.User;
import com.kss.SpringSecurityWithSQL.repository.RoleRepository;
import com.kss.SpringSecurityWithSQL.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public String registerUser(SignUpDto signUpDto){
        if(userRepository.existsByEmail(signUpDto.getEmail()) || userRepository.existsByUserName(signUpDto.getUserName())){
            return "User already exists";
        }

        User user = new User();

        user.setName(signUpDto.getName());
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles=roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));
        userRepository.save(user);

        return "User registered successfully" ;
    }


    public String loginUser( LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Login successful" ;

    }
}
