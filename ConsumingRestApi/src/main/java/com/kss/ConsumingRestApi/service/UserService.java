package com.kss.ConsumingRestApi.service;

import com.kss.ConsumingRestApi.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    @Value("${jsonplaceholder.api.url}")
    private String baseUri;
    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Todo> consumeAPI(){
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/";
        return restTemplate.getForObject(apiUrl, List.class);
    }

    public List<Todo>  consumeApiById(int id){
        String apiUrl = baseUri + "?id=" + id;
        return restTemplate.getForObject(apiUrl, List.class , id);
    }

    public List<Todo>  consumeApiByUserId(int userId){
        String apiUrl = baseUri + "?userId=" + userId;
        return restTemplate.getForObject(apiUrl, List.class , userId);
    }

    public List<Todo>  consumeApiByCompleted(boolean completed){
        String apiUrl = baseUri + "?completed=" + completed;
        return restTemplate.getForObject(apiUrl, List.class , completed);
    }
    
    public List<Todo> consumeApiByUserIdAndId(int userId , int id){
        String apiUrl = baseUri + "/?userId={userId}&id={id}";
        return restTemplate.getForObject(apiUrl , List.class , userId ,id);
    }
}
