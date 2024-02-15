package com.kss.ConsumingRestApiDemo.service;

import com.kss.ConsumingRestApiDemo.exception.NotFoundException;
import com.kss.ConsumingRestApiDemo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestTemplateService {

    private final RestTemplate restTemplate;

    @Value("${jsonplaceholder.api.url}")
    private String baseUri;
    @Autowired
    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Todo> consumeAPI(){
        return restTemplate.getForObject(baseUri, List.class);
    }

    public List<Todo>  consumeApiById(int id){
        String apiUrl = baseUri + "?id=" + id;
        List<Todo> todos = restTemplate.getForObject(apiUrl, List.class );
        if(todos.isEmpty()) throw new NotFoundException("Not found with id: "+id);
        return todos;
    }

    public List<Todo>  consumeApiByUserId(int userId){
        String apiUrl = baseUri + "?userId=" + userId;
        List<Todo> todos = restTemplate.getForObject(apiUrl, List.class);
        if(todos.isEmpty()) throw new NotFoundException("Not found with userId: "+userId);
        return todos;
    }

    public List<Todo>  consumeApiByCompleted(boolean completed){
        String apiUrl = baseUri + "?completed=" + completed;
        return restTemplate.getForObject(apiUrl, List.class);
    }

    public List<Todo> consumeApiByUserIdAndId(int userId , int id){
        String apiUrl = baseUri + "?userId={userId}&id={id}";
        List<Todo> todos = restTemplate.getForObject(apiUrl , List.class , userId ,id);
        if(todos.isEmpty()) throw new NotFoundException("Not found with userId and id: "+userId+"&"+id);
        return todos;
    }
}
