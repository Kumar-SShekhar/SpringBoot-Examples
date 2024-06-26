package com.shekhar.JwtWithLogOutAndRefreshToken.config;

import com.shekhar.JwtWithLogOutAndRefreshToken.repository.UserRepository;
import com.shekhar.JwtWithLogOutAndRefreshToken.service.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutService logoutService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
//                .exceptionHandling(Customizer.withDefaults())

                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/auth/**" ).permitAll();
                    auth.requestMatchers("/demo/check").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement( sm-> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class)
                .logout( logout -> logout.addLogoutHandler(logoutService)
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));

//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());

        return http.build();

    }



}
