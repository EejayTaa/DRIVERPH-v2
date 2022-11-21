package com.ph.DriverPH.module.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;


/**
 * @author Eejay Taa
 */
@Getter
@Setter
@TableName("user")
public class AuthUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String roles;

    private LocalDateTime date;

}
