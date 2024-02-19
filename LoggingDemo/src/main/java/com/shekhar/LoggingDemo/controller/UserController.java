package com.shekhar.LoggingDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j  // by using this we don't need to create instance of Logger. We can use like {log.info()} to show logs
@RestController
@RequestMapping("/logger")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/message")
    public String getMessage(){
        logger.info("info logger");
        logger.warn("warn logger");
        logger.error("error logger");
        logger.trace("trace logger");  // Trace and debug is not enabled by default. we have to enable them manually.
        logger.debug("debug logger");
        log.info("info log");
        return "Hello";
    }

    @GetMapping("/division")
    public int division(){
        int div=0;
        try{
            div=2/0;
        }catch(Exception ex){
            logger.error("can't divide by zero");
            throw new ArithmeticException("Division by zero");
        }
        return div;
    }
}
