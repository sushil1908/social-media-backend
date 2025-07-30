package com.example.socialmedia.controller;

import com.example.socialmedia.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    public ResponseEntity<ApiResponse<String>> ping() {
        return ResponseEntity.ok(
            ApiResponse.success(
                "Pong! Server time: " + LocalDateTime.now()
            )
        );
    }
}
