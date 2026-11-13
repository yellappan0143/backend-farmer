package com.farmride.farmride_backend.controller;


import com.farmride.farmride_backend.dto.UpdateProfileRequest;
import com.farmride.farmride_backend.dto.UserProfileResponse;
import com.farmride.farmride_backend.entity.User;
import com.farmride.farmride_backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.farmride.farmride_backend.service.UserService;

import java.net.Authenticator;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private final UserRepository userRepository;
    private final UserService service;

    public UserProfileController(UserRepository userRepository, UserService service) {
        this.userRepository = userRepository;
        this.service = service;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserResponse (Authentication authentication) {
        String idnetifier = authentication.getName();

        User user = userRepository.findByEmail(idnetifier)
                .orElseGet(() ->
                        userRepository.findByMobileNumber(idnetifier)
                                .orElseThrow(() -> new RuntimeException("User not found"))
                );

        UserProfileResponse profile = new UserProfileResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getMobileNumber(),
                user.getRole()
        );

        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileResponse> updateProfile(
            @RequestBody UpdateProfileRequest request, Authentication authentication){

        User updatedUser = service.updateProfile(
                authentication.getName(), request);

        UserProfileResponse response = new UserProfileResponse(
                updatedUser.getId(),
                updatedUser.getFullName(),
                updatedUser.getEmail(),
                updatedUser.getMobileNumber(),
                updatedUser.getRole()
        );

        return ResponseEntity.ok(response);
    }
}
