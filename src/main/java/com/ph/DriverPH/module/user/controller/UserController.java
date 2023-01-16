package com.ph.DriverPH.module.user.controller;


import com.ph.DriverPH.common.ResponseHandler;
import com.ph.DriverPH.module.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity findUsersById(@PathVariable Long id){
       return ResponseHandler.OK(userService.findUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity findAllUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return ResponseHandler.OK(userService.findAllUsers(page, size));
    }
}
