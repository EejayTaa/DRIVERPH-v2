package com.ph.DriverPH.module.user.service.impl;

import com.ph.DriverPH.exception.ServiceException;
import com.ph.DriverPH.module.user.entity.User;
import com.ph.DriverPH.module.user.mapstruct.IUserConverter;
import com.ph.DriverPH.module.user.repository.IUserRepository;
import com.ph.DriverPH.module.user.response.UserDetailResponse;
import com.ph.DriverPH.module.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public UserDetailResponse findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new ServiceException("User not found."));
        return IUserConverter.INSTANCE.convert(user.get());
    }

    @Override
    public List<UserDetailResponse> findAllUsers(Integer page, Integer size) {
        List<UserDetailResponse> response = new ArrayList<>();
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        users.getContent().forEach(user -> {
            response.add(IUserConverter.INSTANCE.convert(user));
        });
        return response;
    }
}
