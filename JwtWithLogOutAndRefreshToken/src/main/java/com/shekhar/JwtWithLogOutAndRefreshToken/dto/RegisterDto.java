package com.shekhar.JwtWithLogOutAndRefreshToken.dto;

import lombok.Data;

@Data
public class RegisterDto {

    private String name;
    private String userName;
    private String email;
    private String password;
}
