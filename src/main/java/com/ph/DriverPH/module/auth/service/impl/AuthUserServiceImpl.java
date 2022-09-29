package com.ph.DriverPH.module.auth.service.impl;

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
        log.info("AuthUserServiceImpl.register:", authUserRequest);

        authUserRequest.setPassword(passwordEncoder.encode(authUserRequest.getPassword()));
        authUserRequest.setDate(LocalDateTime.now());
        authUserMapper.register(authUserRequest);
    }

    @Override
    public AuthUser getUser(String username) {
        Optional<AuthUser> user = authUserMapper.getUser(username);
        user.orElseThrow(() -> new EntityNotFoundException());
        return user.get();
    }
}
