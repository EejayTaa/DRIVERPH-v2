package com.ph.DriverPH.common.enums;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    BASIC("BASIC", "Basic user"),
    ADMIN("ADMIN", "Administrator");

    private String role;
    private String description;

    UserRoleEnum(String role, String description){
        this.role = role;
        this.description = description;
    }
}
