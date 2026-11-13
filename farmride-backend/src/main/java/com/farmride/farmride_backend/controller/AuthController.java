package com.farmride.farmride_backend.controller;

import com.farmride.farmride_backend.dto.LoginRequest;
import com.farmride.farmride_backend.dto.RegisterRequest;
import com.farmride.farmride_backend.security.JwtService;
import com.farmride.farmride_backend.service.AuthService;
import com.farmride.farmride_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(UserService userService, AuthService authService, JwtService jwtService) {
        this.userService = userService;
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {

        try {
            String message = userService.registerUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest request ){

        try {
            String msg = authService.login(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(msg);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/test-token")
    public ResponseEntity<?> testToken(
            @RequestHeader("Authorization") String authorization) {

        String token = authorization.replace("Bearer ", "");

        boolean valid = jwtService.isTokenValid(token);

        if (!valid) {
            return ResponseEntity.status(401).body("Invalid or expired token");
        }

        return ResponseEntity.ok(
                "Identifier: " + jwtService.extractIdentifier(token)
                        + " | Role: " + jwtService.extractRole(token)
        );
    }
}