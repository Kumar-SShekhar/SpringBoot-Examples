package com.kss.RestTemplateDemo.controller;

import com.kss.RestTemplateDemo.customrresponse.ApiResponse;
import com.kss.RestTemplateDemo.model.User;
import com.kss.RestTemplateDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllData() {
        List<User> users = userService.consumeAPI();
        ApiResponse apiResponse = new ApiResponse("Success", "Users data" , users);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable int id) {
        List<User> user=userService.consumeApiById(id);
        ApiResponse apiResponse = new ApiResponse("Success" , "User found" , user);
        return ResponseEntity.status(HttpStatus.FOUND).body(apiResponse);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<ApiResponse> getUserByUserId(@PathVariable int userId) {
        List<User> user= userService.consumeApiByUserId(userId);
        ApiResponse apiResponse = new ApiResponse("Success" , "User Found", user);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getByCompleted/{completed}")
    public ResponseEntity<List<User>> getUserByCompleted(@PathVariable boolean completed) {
        return new ResponseEntity<>(userService.consumeApiByCompleted(completed), HttpStatus.OK);
    }
}
