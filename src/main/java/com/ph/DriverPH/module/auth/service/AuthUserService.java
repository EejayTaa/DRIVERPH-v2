package com.ph.DriverPH.module.auth.service;


import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.request.AuthUserRequest;
import org.springframework.data.domain.PageRequest;

import java.util.List;


/**
 * @author Eejay Taa
 */
public interface AuthUserService {

    void registerUser (AuthUserRequest authUserRequest);

    AuthUser getUser(String username);

    List<AuthUser> listUser(PageRequest of);

    void deleteUser(String id);
}
