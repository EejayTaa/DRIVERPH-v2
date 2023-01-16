package com.ph.DriverPH.module.auth.service;


import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.request.AuthRequest;

import java.util.Optional;


/**
 * @author Eejay Taa
 */
public interface AuthService {

    void register (AuthRequest authRequest);

    Optional<AuthUser> getUser(String username);

}
