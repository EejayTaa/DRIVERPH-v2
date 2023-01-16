package com.ph.DriverPH.module.user.service;

import com.ph.DriverPH.module.user.response.UserDetailResponse;

import java.util.List;

public interface IUserService {
    UserDetailResponse findUserById(Long id);

    List<UserDetailResponse> findAllUsers(Integer page, Integer size);
}
