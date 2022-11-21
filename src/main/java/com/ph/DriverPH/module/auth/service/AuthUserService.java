package com.ph.DriverPH.module.auth.service;


import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.request.AuthUserRequest;

import java.util.Optional;

/**
 * @author Eejay Taa
 */
public interface AuthUserService {

    void register (AuthUserRequest authUserRequest);

    Optional<AuthUser> getUser(String username);

}
