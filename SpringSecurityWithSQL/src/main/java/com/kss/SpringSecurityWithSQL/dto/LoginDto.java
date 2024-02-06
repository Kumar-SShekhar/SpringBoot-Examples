package com.kss.SpringSecurityWithSQL.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginDto {

    private String userName;
    private String password;
}
