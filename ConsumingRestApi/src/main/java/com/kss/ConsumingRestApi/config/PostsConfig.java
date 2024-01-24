package com.kss.ConsumingRestApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PostsConfig {

    @Bean
    public RestTemplate restTemplate1(){
        return new RestTemplate();
    }
}
