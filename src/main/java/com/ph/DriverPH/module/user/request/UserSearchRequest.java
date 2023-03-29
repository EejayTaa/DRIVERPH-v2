package com.ph.DriverPH.module.user.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserSearchRequest {

    private String username;
    private String email;
    private Integer page;
    private Integer size;
}
