package com.kss.ConsumingRestApi.service;

import com.kss.ConsumingRestApi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    @Value("${jsonplaceholder.api.url}")
    private String baseURI;
    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> consumeAPI(){
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/";
        return restTemplate.getForObject(apiUrl, List.class);
    }

    public List<User>  consumeApiById(int id){
        String apiUrl = baseURI + "?id=" + id;
        return restTemplate.getForObject(apiUrl, List.class );
    }

    public List<User>  consumeApiByUserId(int userId){
        String apiUrl = baseURI + "?userId=" + userId;
        return restTemplate.getForObject(apiUrl, List.class);
    }

    public List<User>  consumeApiByCompleted(boolean completed){
        String apiUrl = baseURI + "?completed=" + completed;
        return restTemplate.getForObject(apiUrl, List.class);
    }
}
