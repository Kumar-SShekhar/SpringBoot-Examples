package com.kss.SpringSecurityDemo2.service;

import com.kss.SpringSecurityDemo2.config.JwtService;
import com.kss.SpringSecurityDemo2.dto.AuthenticationResponse;
import com.kss.SpringSecurityDemo2.dto.ChangePasswordDto;
import com.kss.SpringSecurityDemo2.entity.User;
import com.kss.SpringSecurityDemo2.repository.UserRepository;
import com.kss.SpringSecurityDemo2.utils.EmailUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final EmailUtil emailUtil;

    public String changePassword(ChangePasswordDto changePasswordDto, HttpServletRequest request){
        final String authHeader= request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        final String userEmail;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return null;
        }
        jwt=authHeader.substring(7);
        userEmail=jwtService.extractUsername(jwt);
        if(userEmail!=null) {
            var user = userRepository.findByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(jwt, user)) {
                if (!passwordEncoder.matches(changePasswordDto.getCurrentPassword(), user.getPassword())) {
                    return "Entered current password is wrong";
                }

                if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
                    return "new password and confirm password doesn't match";
                }

                user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
                userRepository.save(user);

            }
        }
        return "Password changed successfully";
    }

    public String sendEmail(String email){
        return emailUtil.sendEmail(email);
    }

    public String sendEmailOtp(String email){
        return emailUtil.sendEmailOtp(email);
    }


}
