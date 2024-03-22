//package com.kss.SpringSecurityDemo2.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.kss.SpringSecurityDemo2.customresponse.ApiResponse;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.charset.StandardCharsets;
//
//@Component
////@Slf4j
//public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//
//    private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
//
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Set the status code to 403 Forbidden
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // Set content type to JSON
//        response.setCharacterEncoding(StandardCharsets.UTF_8.name()); // Set character encoding
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            log.warn("User: " + auth.getName() + " attempted to access the protected URL: " + request.getRequestURI());
//        }
//        // ObjectMapper is used to convert java objects to JSON and vice-versa
//        ObjectMapper objectMapper = new ObjectMapper();
//        ApiResponse apiResponse = new ApiResponse("403", "Access Denied : Attempted to access the protected URL", null);
//        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
//
//        PrintWriter out = response.getWriter();
//        out.print(jsonResponse);
//        out.flush();
//
//    }
//}
