package com.ph.DriverPH.module.auth.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data

public class AuthUserRequest {

    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    private String password;

    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Role is required.")
    private String roles;

    @JsonIgnore
    private LocalDateTime date;

}
