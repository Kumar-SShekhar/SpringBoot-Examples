package com.kss.JwtWithSQL.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {

    @NotNull
    private String email;
    @NotNull
    private String password;
}
