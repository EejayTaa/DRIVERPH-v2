package com.ph.DriverPH.manager;

import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.service.AuthService;
import com.ph.DriverPH.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final AuthService authService;
    private final SecurityConfiguration bCryptPasswordEncoder;

    @Autowired
    public CustomAuthenticationManager(@Lazy AuthService authService, SecurityConfiguration bCryptPasswordEncoder) {
        this.authService = authService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //Extract the authentication credentials, compare them into the credentials inside our database.
        //Use the user service to find user inside the database
        Optional<AuthUser> user = authService.getUser(authentication.getName());
        user.orElseThrow(EntityNotFoundException::new);
        //Crendentials validation and decryption of password using bcrypt
        if(!bCryptPasswordEncoder.passwordEncoder().matches(authentication.getCredentials().toString(),user.get().getPassword())){
            throw new BadCredentialsException("Username or password is incorrect.");
        }

        //return token if valid credentials.
        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.get().getUsername());
    }
}
