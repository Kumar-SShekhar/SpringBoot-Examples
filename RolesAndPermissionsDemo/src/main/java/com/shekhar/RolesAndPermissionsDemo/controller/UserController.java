package com.shekhar.RolesAndPermissionsDemo.controller;

import com.shekhar.RolesAndPermissionsDemo.dto.AuthenticationResponse;
import com.shekhar.RolesAndPermissionsDemo.dto.LoginDto;
import com.shekhar.RolesAndPermissionsDemo.dto.RegisterDto;
import com.shekhar.RolesAndPermissionsDemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterDto registerDto){
        return userService.register(registerDto);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

    @GetMapping("/demo")
    public String getMessage(){
        return "From demo endpoint";
    }

}
