package com.kss.SpringSecurityDemo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class LoginDto {

    private String email;
    private String password;
}
