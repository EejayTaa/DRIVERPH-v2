package com.ph.DriverPH.module.auth.service.impl;

import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.mapper.AuthUserMapper;
import com.ph.DriverPH.module.auth.request.AuthUserRequest;
import com.ph.DriverPH.module.auth.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    AuthUserMapper authUserMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void register(AuthUserRequest authUserRequest) {

        //check if user already exists in the database
        Optional<AuthUser> user = getUser(authUserRequest.getUsername());
        if(user.isPresent()){
            throw new ServiceException("Username already exists.");
        }

        //set created date
        LocalDateTime date = LocalDateTime.now();
        authUserRequest.setDate(date);

        //encrypt user password
        authUserRequest.setPassword(passwordEncoder.encode(authUserRequest.getPassword()));

        log.info("AuthUserServiceImpl.register:", authUserRequest);
        authUserMapper.register(authUserRequest);
    }

    @Override
    public Optional<AuthUser> getUser(String username) {
        Optional<AuthUser> user = authUserMapper.getUser(username);
        return user;
    }
}
