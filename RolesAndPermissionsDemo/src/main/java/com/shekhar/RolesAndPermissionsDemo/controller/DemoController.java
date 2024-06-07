package com.shekhar.RolesAndPermissionsDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class DemoController {

    @GetMapping("/demo")
    public String getMessage(){
        return "From demo endpoint";
    }
}
