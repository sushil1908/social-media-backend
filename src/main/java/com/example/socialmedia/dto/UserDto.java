package com.example.socialmedia.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
