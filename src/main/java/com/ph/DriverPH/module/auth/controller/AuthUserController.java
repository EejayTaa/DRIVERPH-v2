package com.ph.DriverPH.module.auth.controller;

import com.ph.DriverPH.common.ResponseHandler;
import com.ph.DriverPH.module.auth.request.AuthUserRequest;
import com.ph.DriverPH.module.auth.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eejay Taa
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService authUserService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated AuthUserRequest request){
        authUserService.register(request);
        return ResponseHandler.CREATED();
    }



}
