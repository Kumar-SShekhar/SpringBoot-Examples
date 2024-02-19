package com.kss.SpringSecurityWithSQL.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SignUpDto {

    private String name;
    private String userName;
    private String email;
    private String password;
}
