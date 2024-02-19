package com.kss.RestTemplateDemo.service;

import com.kss.RestTemplateDemo.exception.BadRequestException;
import com.kss.RestTemplateDemo.exception.NotFoundException;
import com.kss.RestTemplateDemo.model.User;
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
        if(id<=0){
            throw new BadRequestException("Error", "Invalid id");
        }
        String apiUrl = baseURI + "?id=" + id;
        List<User> user = restTemplate.getForObject(apiUrl, List.class );
        if(user.isEmpty()){
            throw new NotFoundException("Error" , "NotFound with id: "+id);
        }
        return user;
    }

    public List<User>  consumeApiByUserId(int userId){
        if(userId<=0){
            throw new BadRequestException("Error", "Bad Request");
        }
        String apiUrl = baseURI + "?userId=" + userId;
        List<User> users=restTemplate.getForObject(apiUrl, List.class);
        if(users.isEmpty()){
            throw new NotFoundException("Error", "Not found with userId:" +userId);
        }
        return restTemplate.getForObject(apiUrl, List.class);
    }

    public List<User>  consumeApiByCompleted(boolean completed){
        String apiUrl = baseURI + "?completed=" + completed;
        return restTemplate.getForObject(apiUrl, List.class);
    }
}
