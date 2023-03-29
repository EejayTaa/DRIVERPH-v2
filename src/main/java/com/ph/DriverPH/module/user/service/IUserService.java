package com.ph.DriverPH.module.user.service;

import com.ph.DriverPH.module.user.request.UserSearchRequest;
import com.ph.DriverPH.module.user.response.UserDetailResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    UserDetailResponse findUserById(Long id);

    Page<UserDetailResponse> findAllUsers(UserSearchRequest request);
}
