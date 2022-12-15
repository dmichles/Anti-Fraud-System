package com.example.antifraud.configuration;


import com.example.antifraud.models.constants.UserAuthority;
import com.example.antifraud.models.listeners.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public RestAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and().authorizeRequests().mvcMatchers(HttpMethod.POST,"/api/auth/user").permitAll()
                .and().authorizeRequests().mvcMatchers(HttpMethod.GET, "/api/auth/list")
                .hasAnyAuthority("ADMINISTRATOR","SUPPORT")
                .and().authorizeRequests().mvcMatchers(HttpMethod.PUT,"/api/auth/role")
                .hasAuthority("ADMINISTRATOR")
                .and().authorizeRequests().mvcMatchers(HttpMethod.DELETE,"/api/auth/user/*")
                .hasAuthority("ADMINISTRATOR")
                .and().authorizeRequests().mvcMatchers(HttpMethod.POST,"/api/antifraud/transaction")
                .hasAuthority("MERCHANT")
                .and().authorizeRequests().mvcMatchers(HttpMethod.GET,"/api/antifraud/transaction/history")
                .hasAuthority("MERCHANT")
                .and().authorizeRequests().mvcMatchers(HttpMethod.GET,"/api/antifraud/transaction/history/*")
                .hasAuthority("MERCHANT");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
