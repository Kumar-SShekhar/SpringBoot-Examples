package com.kss.ConsumingRestApiDemo.controller;

import com.kss.ConsumingRestApiDemo.customrresponse.ApiResponse;
import com.kss.ConsumingRestApiDemo.model.Todo;
import com.kss.ConsumingRestApiDemo.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resttemplate")
public class RestTemplateController {

    private final RestTemplateService restTemplateService;

    @Autowired
    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllData() {
        List<Todo> todo = restTemplateService.consumeAPI();
        ApiResponse apiResponse = new ApiResponse("Success", "Data Found", todo);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable int id) {
        List<Todo> todo = restTemplateService.consumeApiById(id);
        ApiResponse apiResponse = new ApiResponse("Success", "Data Found", todo);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<ApiResponse> getUserByUserId(@PathVariable int userId) {
        List<Todo> todo = restTemplateService.consumeApiByUserId(userId);
        ApiResponse apiResponse = new ApiResponse("Success", "Data Found", todo);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @GetMapping("/getByCompleted/{completed}")
    public ResponseEntity<ApiResponse> getUserByCompleted(@PathVariable boolean completed) {
        List<Todo> todo = restTemplateService.consumeApiByCompleted(completed);
        ApiResponse apiResponse = new ApiResponse("Success", "Data Found", todo);
        return ResponseEntity.status(200).body(apiResponse);
    }

    @GetMapping("/getUserByUserIdAndId/{userId}/{id}")
    public ResponseEntity<ApiResponse> getUserByUserIdAndId(@PathVariable int userId , @PathVariable int id){
        List<Todo> todo = restTemplateService.consumeApiByUserIdAndId(userId , id);
        ApiResponse apiResponse = new ApiResponse("Success", "Data Found", todo);
        return ResponseEntity.status(200).body(apiResponse);
    }
}
