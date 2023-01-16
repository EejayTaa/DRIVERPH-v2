package com.ph.DriverPH.module.user.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDetailResponse implements Serializable {

    private Long id;

    private String email;

    private String username;
}
