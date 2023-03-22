package com.example.clickintegration.controller;

import com.example.clickintegration.dto.request.UserLoginRequest;
import com.example.clickintegration.dto.request.UserRegisterDto;
import com.example.clickintegration.dto.response.UserLoginResponse;
import com.example.clickintegration.exception.ApiResponse;
import com.example.clickintegration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody UserRegisterDto dto){
        return userService.add(dto);
    }

    @PostMapping("/sign-in")
    public UserLoginResponse login(
            @RequestBody UserLoginRequest userLoginRequest
    ){
        return userService.login(userLoginRequest);
    }
}
