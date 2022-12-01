package com.ph.DriverPH.module.auth.service.impl;

import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.repository.IAuthUserRepository;
import com.ph.DriverPH.module.auth.request.AuthUserRequest;
import com.ph.DriverPH.module.auth.service.AuthUserService;
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
public class AuthUserServiceImpl implements AuthUserService {

    private final IAuthUserRepository iAuthUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(AuthUserRequest request) {
        //Check if user already exists in the database
        Optional<AuthUser> user = iAuthUserRepository.findAuthUserByUsername(request.getUsername());
        if(user.isPresent()){
            throw new ServiceException("Username already exists.");
        }
        //Encrypt user password
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        log.info("---AuthUserServiceImpl---register:{}", request);

        AuthUser authUser = AuthUser.of(request.getUsername(), request.getPassword(), request.getEmail());
        authUser.setCreatedDate(LocalDateTime.now());

        iAuthUserRepository.save(authUser);
    }

    @Override
    public Optional<AuthUser> getUser(String username) {
        Optional<AuthUser> user = iAuthUserRepository.findAuthUserByUsername(username);
        return user;
    }
}
