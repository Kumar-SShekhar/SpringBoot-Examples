package com.kss.SpringSecurityDemo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class RegisterDto {

    private String name;
//    private String userName;
    private String email;
    private String password;
}
