package com.kss.WebClientDemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class BadRequestException extends RuntimeException{

    private final String status;
    private final String message;
}
