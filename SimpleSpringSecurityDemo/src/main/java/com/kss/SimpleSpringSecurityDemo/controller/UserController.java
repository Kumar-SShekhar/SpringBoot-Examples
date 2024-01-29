package com.kss.SimpleSpringSecurityDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping
public class UserController {

    @GetMapping("/public")
    public String getAllPublic(){
        return "Admin";
    }

    @GetMapping("/public/abc")
    public String getAllPublicAbc(){
        return "Admin abc";
    }

    @GetMapping("/p")
    public String getOther(){
        return "Others";
    }
}
