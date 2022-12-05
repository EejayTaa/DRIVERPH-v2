package com.ph.DriverPH.module.auth.controller;

import com.ph.DriverPH.common.ResponseHandler;
import com.ph.DriverPH.module.auth.entity.AuthUser;
import com.ph.DriverPH.module.auth.request.AuthUserRequest;
import com.ph.DriverPH.module.auth.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        authUserService.registerUser(request);
        return ResponseHandler.CREATED();
    }

    @GetMapping("/user/list")
    public ResponseEntity listUsers(@RequestParam(defaultValue = "1") Integer size, @RequestParam(defaultValue = "0") Integer page){
        List<AuthUser> list = authUserService.listUser(PageRequest.of(page, size));
        return ResponseHandler.OK(list);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUser(@PathVariable String id){
        AuthUser user = authUserService.getUser(id);
        return ResponseHandler.OK(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        authUserService.deleteUser(id);
        return ResponseHandler.OK();
    }


}
