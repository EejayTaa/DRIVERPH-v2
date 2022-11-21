package com.ph.DriverPH.module.auth.mapper;

import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.request.AuthUserRequest;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * @author Eejay Taa
 */
@Mapper
public interface AuthUserMapper {

    void register(AuthUserRequest authUserRequest);

    Optional<AuthUser> getUser(String username);
}
