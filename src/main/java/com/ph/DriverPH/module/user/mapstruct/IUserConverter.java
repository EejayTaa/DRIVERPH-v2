package com.ph.DriverPH.module.user.mapstruct;

import com.ph.DriverPH.module.user.entity.User;
import com.ph.DriverPH.module.user.response.UserDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface IUserConverter {
    IUserConverter INSTANCE = Mappers.getMapper(IUserConverter.class);

    UserDetailResponse convert(User user);

}
