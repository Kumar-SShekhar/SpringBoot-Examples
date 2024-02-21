package com.kss.SpringSecurityDemo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordDto {

    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
