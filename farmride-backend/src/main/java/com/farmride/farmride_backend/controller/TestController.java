package com.farmride.farmride_backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/profile")
    public String getProfile(Authentication authentication) {

        return "Welcome to FarmRide! "
                + "Logged-in user: " + authentication.getName()
                + " | Role: " + authentication.getAuthorities();
    }
}