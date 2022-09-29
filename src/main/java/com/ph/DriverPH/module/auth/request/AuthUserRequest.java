package com.ph.DriverPH.module.auth.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data

public class AuthUserRequest {


    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    private String password;

    private String email;

    private LocalDateTime date;

}
