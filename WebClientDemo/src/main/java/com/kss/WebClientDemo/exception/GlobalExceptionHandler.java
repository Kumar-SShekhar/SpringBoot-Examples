package com.kss.WebClientDemo.exception;

import com.kss.WebClientDemo.customresponse.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ResponseStatus(HttpStatus.NOT_FOUND)

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
        ApiResponse apiResponse = new ApiResponse("Error", ex.getMessage(), null);
        return ResponseEntity.status(500).body(apiResponse);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundException(Exception ex){
        ApiResponse apiResponse = new ApiResponse("error" , ex.getMessage(), null);
        return ResponseEntity.status(404).body(apiResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handleBadRequestException(Exception ex){
        ApiResponse apiResponse = new ApiResponse("error" , ex.getMessage() , null);
        return ResponseEntity.status(404).body(apiResponse);
    }
}
