package com.shekhar.JwtWithLogOutAndRefreshToken.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {

    private String email;
    private String password;
}
