package com.kss.SpringSecurityDemo2.customresponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private String status;
    private String message;
    private Object data;
}
