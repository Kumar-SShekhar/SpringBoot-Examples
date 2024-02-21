package com.shekhar.JwtWithLogOutAndRefreshToken.service;

import com.shekhar.JwtWithLogOutAndRefreshToken.config.JwtService;
import com.shekhar.JwtWithLogOutAndRefreshToken.dto.AuthenticationResponse;
import com.shekhar.JwtWithLogOutAndRefreshToken.dto.LoginDto;
import com.shekhar.JwtWithLogOutAndRefreshToken.dto.RegisterDto;
import com.shekhar.JwtWithLogOutAndRefreshToken.entity.Role;
import com.shekhar.JwtWithLogOutAndRefreshToken.entity.User;
import com.shekhar.JwtWithLogOutAndRefreshToken.repository.TokenRepository;
import com.shekhar.JwtWithLogOutAndRefreshToken.repository.UserRepository;
import com.shekhar.JwtWithLogOutAndRefreshToken.token.Token;
import com.shekhar.JwtWithLogOutAndRefreshToken.token.TokenType;
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
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);  // If we use void as return type
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
