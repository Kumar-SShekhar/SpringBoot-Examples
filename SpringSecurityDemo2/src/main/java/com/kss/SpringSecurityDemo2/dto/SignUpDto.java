package com.kss.SpringSecurityDemo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private String name;
    private String userName;
    private String email;
    private String password;
}
