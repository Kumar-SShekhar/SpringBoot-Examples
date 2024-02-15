package com.kss.RestTemplateDemo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException{

    private final String status;
    private final String message;

}
