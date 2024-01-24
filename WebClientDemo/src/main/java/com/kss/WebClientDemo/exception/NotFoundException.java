package com.kss.WebClientDemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotFoundException extends RuntimeException{

    private String status;
    private String message;
}
