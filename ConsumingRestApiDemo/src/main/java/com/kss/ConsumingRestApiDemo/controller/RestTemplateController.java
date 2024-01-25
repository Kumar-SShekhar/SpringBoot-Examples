package com.kss.ConsumingRestApiDemo.controller;

import com.kss.ConsumingRestApiDemo.model.Todo;
import com.kss.ConsumingRestApiDemo.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Todo>> getAllData() {
        return new ResponseEntity<>(restTemplateService.consumeAPI(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<List<Todo>> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(restTemplateService.consumeApiById(id), HttpStatus.OK);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<Todo>> getUserByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(restTemplateService.consumeApiByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/getByCompleted/{completed}")
    public ResponseEntity<List<Todo>> getUserByCompleted(@PathVariable boolean completed) {
        return new ResponseEntity<>(restTemplateService.consumeApiByCompleted(completed), HttpStatus.OK);
    }
}
