package com.kss.SpringSecurityDemo2.service;

import com.kss.SpringSecurityDemo2.config.JwtService;
import com.kss.SpringSecurityDemo2.dto.AuthenticationResponse;
import com.kss.SpringSecurityDemo2.dto.ForgotPasswordVerificationDto;
import com.kss.SpringSecurityDemo2.dto.LoginDto;
import com.kss.SpringSecurityDemo2.dto.RegisterDto;
import com.kss.SpringSecurityDemo2.entity.Role;
import com.kss.SpringSecurityDemo2.entity.User;
import com.kss.SpringSecurityDemo2.repository.TokenRepository;
import com.kss.SpringSecurityDemo2.repository.UserRepository;
import com.kss.SpringSecurityDemo2.token.Token;
import com.kss.SpringSecurityDemo2.token.TokenType;
import com.kss.SpringSecurityDemo2.utils.EmailUtil;
import com.kss.SpringSecurityDemo2.utils.OtpGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final EmailUtil emailUtil;
    private final OtpGenerator otpGenerator;
    private final OtpService otpService;

    public AuthenticationResponse registerUser(RegisterDto registerDto){
        User user = new User();
        user.setName(registerDto.getName());
        user.setUserName(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        String jwtRefreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }


    public AuthenticationResponse loginUser(LoginDto loginDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        String jwtRefreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

    public String forgotPassword(String email){
        User user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not Found with this email"));
        emailUtil.sendEmailOtp(email);
        return "Otp sent successfully. Please type password and verify otp";
    }
    public String verifyForgotPassword(ForgotPasswordVerificationDto verificationDto){
        User user = userRepository.findByEmail(verificationDto.getEmail()).orElseThrow(()-> new RuntimeException("User not found with this email"));
        String enteredOtp = verificationDto.getOtp();

        if(!verificationDto.getNewPassword().equals(verificationDto.getConfirmPassword())) {
            return "Password doesn't match";
        }
        else if(passwordEncoder.matches(verificationDto.getNewPassword(), user.getPassword() )) {
            return "Current password and new password cannot be same";
        }

        else if(otpService.verifyOtp(user.getEmail(), enteredOtp)){
            user.setPassword(passwordEncoder.encode(verificationDto.getNewPassword()));
            userRepository.save(user);
            return "Password changed Successfully";
        }
        return "Otp is invalid";



//        if(otpService.verifyOtp(user.getEmail(), enteredOtp)){
//            String currentPassword = user.getPassword();
//            String hashedNewPassword = passwordEncoder.encode(verificationDto.getNewPassword());
//            if(passwordEncoder.matches(verificationDto.getNewPassword(), user.getPassword() )) {
//                return "Current password and new password cannot be same";
//            }
//            else if(!verificationDto.getNewPassword().equals(verificationDto.getConfirmPassword())) {
//                return "Password doesn't match";
//            }
//        }
//        user.setPassword(passwordEncoder.encode(verificationDto.getNewPassword()));
//        userRepository.save(user);
//        return "Password changed Successfully";
    }

//    public String loginBy(String email){
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with this email"));
//        String generatedOtp = otpGenerator.generateOtp();
//        String sendOtp = emailUtil.sendEmailOtp(email);
//        return "OTP sent successfully. Please verify to login";
//    }
//    public AuthenticationResponse verifyLoginOtp(String email,String otp){
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with this email"));
//
//    }


    public AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response){
        final String authHeader= request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwtRefreshToken;
        final String userEmail;
        AuthenticationResponse authResponse=null;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return null;
        }
        jwtRefreshToken=authHeader.substring(7);
        userEmail=jwtService.extractUsername(jwtRefreshToken);
        if(userEmail!=null) {
            var user = userRepository.findByEmail(userEmail).orElseThrow();
            if(jwtService.isTokenValid(jwtRefreshToken, user)){
                var jwtAccessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, jwtAccessToken);
                authResponse = AuthenticationResponse.builder()
                        .accessToken(jwtAccessToken)
                        .refreshToken(jwtRefreshToken)
                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);  // If we use void as return type but need to                                                                                                return authResponse
            }
        }
        return authResponse;
    }

    private void saveUserToken(User user , String jwtToken){
        Token token = Token.builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .user(user)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if(validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
