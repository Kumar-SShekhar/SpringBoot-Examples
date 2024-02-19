package com.shekhar.ProfilesDemo.controller;

import com.shekhar.ProfilesDemo.model.User;
import com.shekhar.ProfilesDemo.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public User saveUser(@RequestBody User user){
         userRepository.save(user);
         return user;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @GetMapping("/byId/{id}")
    public User getById(@PathVariable Long id){
        return userRepository.findById(id).orElse(null);
    }
}
