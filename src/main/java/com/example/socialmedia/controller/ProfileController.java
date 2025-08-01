package com.example.socialmedia.controller;

import com.example.socialmedia.dto.ApiResponse;
import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    // Get current user's profile
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDto>> getMyProfile(Authentication authentication) {
        String username = authentication.getName();
        UserDto userDto = userService.getUserByUsername(username);
        return ResponseEntity.ok(ApiResponse.success("Profile fetched", userDto));
    }

    // Get another user's profile by username
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse<UserDto>> getUserProfile(@PathVariable String username) {
        UserDto userDto = userService.getUserByUsername(username);
        return ResponseEntity.ok(ApiResponse.success("Profile fetched", userDto));
    }

    // Update current user's profile (firstName, lastName only)
    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UserDto>> updateMyProfile(Authentication authentication, @RequestBody com.example.socialmedia.dto.UserProfileUpdateDto profileDto) {
        String username = authentication.getName();
        UserDto updated = userService.updateUserProfile(username, profileDto);
        return ResponseEntity.ok(ApiResponse.success("Profile updated", updated));
    }
}
