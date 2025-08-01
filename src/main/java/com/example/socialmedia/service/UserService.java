package com.example.socialmedia.service;

import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.dto.UserLoginDto;
import com.example.socialmedia.dto.UserRegistrationDto;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public UserDto registerUser(UserRegistrationDto registrationDto) {
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEnabled(true);
        User saved = userRepository.save(user);
        return toDto(saved);
    }

    @Autowired
    private com.example.socialmedia.security.JwtUtil jwtUtil;

    public AuthResponse loginAndGetToken(UserLoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsernameOrEmail())
                .or(() -> userRepository.findByEmail(loginDto.getUsernameOrEmail()))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(toDto(user), token);
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toDto(user);
    }

    public UserDto updateUserProfile(String username, com.example.socialmedia.dto.UserProfileUpdateDto profileDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setFirstName(profileDto.getFirstName());
        user.setLastName(profileDto.getLastName());
        User saved = userRepository.save(user);
        return toDto(saved);
    }

    // Legacy method for compatibility (can be removed if not used)
    public UserDto authenticateUser(UserLoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsernameOrEmail())
                .or(() -> userRepository.findByEmail(loginDto.getUsernameOrEmail()))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return toDto(user);
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }
}
