package com.kss.JwtWithSQL.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDto {

    @NotNull
    private String name;
    @NotNull
    private String userName;
    @Email
    private String email;
    @NotNull
    private String password;
}
