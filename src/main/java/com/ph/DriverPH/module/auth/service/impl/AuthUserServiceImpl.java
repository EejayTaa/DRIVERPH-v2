package com.ph.DriverPH.module.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.ph.DriverPH.common.enums.UserRoleEnum;
import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.repository.IAuthUserRepository;
import com.ph.DriverPH.module.auth.request.AuthUserRequest;
import com.ph.DriverPH.module.auth.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
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
    public void registerUser(AuthUserRequest request) {
        //Check if user already exists in the database
        Optional<AuthUser> user = iAuthUserRepository.findAuthUserByUsername(request.getUsername());
        if(user.isPresent()){
            throw new ServiceException("Username already exists.");
        }
        //Encrypt user password
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        log.info("---AuthUserServiceImpl---register:{}", JSON.toJSONString(request));

        AuthUser authUser = AuthUser.of(request.getUsername(), request.getPassword(), request.getEmail(), request.getRole());
        authUser.setCreatedDate(LocalDateTime.now());

        iAuthUserRepository.save(authUser);
    }

    @Override
    public AuthUser getUser(String username) {
        Optional<AuthUser> user = iAuthUserRepository.findAuthUserByUsername(username);
        user.orElseThrow(() -> new ServiceException("User not found.", HttpStatus.NOT_FOUND));
        return user.get();
    }

    @Override
    public List<AuthUser> listUser(PageRequest of) {
        return iAuthUserRepository.findAll(of).getContent();
    }

    @Override
    public void deleteUser(String id) {
        AuthUser user = this.getUser(id);
        if(!UserRoleEnum.ADMIN.getRole().equals(user.getRole())){
            throw new ServiceException("You do not have permission to this action.");
        }
        iAuthUserRepository.delete(user);
    }

}
