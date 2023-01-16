package com.ph.DriverPH.module.auth.controller;

import com.ph.DriverPH.common.ResponseHandler;
import com.ph.DriverPH.module.auth.request.AuthRequest;
import com.ph.DriverPH.module.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated AuthRequest request){
        authService.register(request);
        return ResponseHandler.CREATED();
    }

}
