package com.kss.SimpleSpringSecurityDemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping
public class UserController {

    @GetMapping("/public")
    public String getPublic(){
        return "Public";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAdmin")
    public String getAdmin(){
        return "Admin xyz";
    }

    @GetMapping("/getOthers")
    public String getOther(){
        return "Others";
    }

}
