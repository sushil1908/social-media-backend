package com.example.socialmedia.controller;

import com.example.socialmedia.dto.ApiResponse;
import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.dto.UserLoginDto;
import com.example.socialmedia.dto.UserRegistrationDto;
import com.example.socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> register(@RequestBody UserRegistrationDto registrationDto) {
        UserDto userDto = userService.registerUser(registrationDto);
        return ResponseEntity.ok(ApiResponse.success("Registration successful", userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<com.example.socialmedia.service.AuthResponse>> login(@RequestBody UserLoginDto loginDto) {
        com.example.socialmedia.service.AuthResponse authResponse = userService.loginAndGetToken(loginDto);
        return ResponseEntity.ok(ApiResponse.success("Login successful", authResponse));
    }
}
