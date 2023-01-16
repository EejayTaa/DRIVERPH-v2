package com.ph.DriverPH.security;


import com.ph.DriverPH.filter.AuthenticationFilter;

import com.ph.DriverPH.filter.ExceptionHandlerFilter;
import com.ph.DriverPH.filter.JWTAuthorizationFilter;
import com.ph.DriverPH.manager.CustomAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    private final CustomAuthenticationManager customAuthenticationManager;
    @Autowired
    public SecurityConfiguration(@Lazy CustomAuthenticationManager customAuthenticationManager) {
        this.customAuthenticationManager = customAuthenticationManager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/auth/login");
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authz -> authz
                        .antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                        .anyRequest().authenticated())
                .httpBasic().disable()
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
