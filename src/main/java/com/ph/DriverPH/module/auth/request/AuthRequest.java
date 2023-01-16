package com.ph.DriverPH.module.auth.request;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class AuthRequest {

    @NotBlank(message = "Username is a required.")
    private String username;

    @NotBlank(message = "Password is a required.")
    private String password;

    @NotBlank(message = "Email is a required.")
    private String email;

    private String role;

    @JsonIgnore
    private LocalDateTime date;

}
