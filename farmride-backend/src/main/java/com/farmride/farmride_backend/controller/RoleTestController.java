package com.farmride.farmride_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleTestController {

    @GetMapping("/api/admin/dashboard")
    public String adminDashboard() {
        return "Welcome to FarmRide Admin Dashboard!";
    }

    @GetMapping("/api/farmer/dashboard")
    public String farmerDashboard() {
        return "Welcome to FarmRide Farmer Dashboard!";
    }

    @GetMapping("/api/equipment-owner/dashboard")
    public String equipmentOwnerDashboard() {
        return "Welcome to FarmRide Equipment Owner Dashboard!";
    }
}