package com.example.socialmedia.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    private String usernameOrEmail;
    private String password;
}
