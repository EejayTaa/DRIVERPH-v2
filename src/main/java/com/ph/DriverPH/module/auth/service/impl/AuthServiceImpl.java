package com.ph.DriverPH.module.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.repository.IAuthRepository;
import com.ph.DriverPH.module.auth.request.AuthRequest;
import com.ph.DriverPH.module.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Eejay Taa
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final IAuthRepository iAuthRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(AuthRequest request) {
        //Check if user already exists in the database
        Optional<AuthUser> user = this.getUser(request.getUsername());
        if(user.isPresent()){
            throw new ServiceException("Username already exists.");
        }
        //Encrypt user password
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        log.info("=====AuthUserServiceImpl=====register:{}=====", request);

        AuthUser authUser = AuthUser.of(request.getUsername(), request.getPassword(), request.getEmail());
        authUser.setCreatedDate(LocalDateTime.now());
        iAuthRepository.save(authUser);
    }

    @Override
    public Optional<AuthUser> getUser(String username) {
        return iAuthRepository.findAuthUserByUsername(username);
    }
}
