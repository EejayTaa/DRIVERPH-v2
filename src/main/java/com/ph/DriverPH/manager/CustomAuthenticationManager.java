package com.ph.DriverPH.manager;

import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.service.AuthUserService;
import com.ph.DriverPH.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;



@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final AuthUserService authUserService;
    private final SecurityConfiguration bCryptPasswordEncoder;

    @Autowired
    public CustomAuthenticationManager(@Lazy AuthUserService authUserService, SecurityConfiguration bCryptPasswordEncoder) {
        this.authUserService = authUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //extract the authentication credentials, compare them into the credentials inside our database.

        //use the user service to find user inside the database
        AuthUser user = authUserService.getUser(authentication.getName());

        //crendentials validation and decryption of password using bcrypt
        if(!bCryptPasswordEncoder.passwordEncoder().matches(authentication.getCredentials().toString(),user.getPassword())){
            throw new BadCredentialsException("Username or password is incorrect.");
        }

        //return token if valid credentials.
        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getUsername());
    }
}
