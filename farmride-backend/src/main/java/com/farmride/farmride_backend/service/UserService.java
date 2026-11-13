package com.farmride.farmride_backend.service;

import com.farmride.farmride_backend.dto.RegisterRequest;
import com.farmride.farmride_backend.dto.UpdateProfileRequest;
import com.farmride.farmride_backend.entity.User;
import com.farmride.farmride_backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String registerUser(RegisterRequest request) {

        if (request.getFullName() == null || request.getFullName().isBlank()) {
            throw new IllegalArgumentException("Full name is required");
        }

        if (request.getPassword() == null || request.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must contain at least 8 characters");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }


        User user = new User();
        user.setFullName(request.getFullName());
        user.setMobileNumber(request.getMobileNumber());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(request.getConfirmPassword()));

//        String email = request.getEmail().trim();
//
//        if(email.equalsIgnoreCase("admin@gmail.com"))
//            user.setRole("ADMIN");
//        else
            user.setRole("FARMER");

        userRepository.save(user);

        return "Thank You!" + user.getFullName() + "Registered Successfully!";
    }


    public User updateProfile(String identifier, UpdateProfileRequest request){

        User user = userRepository.findByEmail(identifier)
                .or(() -> userRepository.findByMobileNumber(identifier))
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (request.getFullName() == null || request.getFullName().isBlank()){
            throw new RuntimeException("Full name is a required field.");
        };


        userRepository.findByMobileNumber(request.getMobileNumber())
                .ifPresent(existingUser -> {
                    if (existingUser.getId() != (user.getId())) {
                        throw new RuntimeException("Mobile number is already registered");
                    }
                });


        user.setFullName(request.getFullName());
        user.setMobileNumber(request.getMobileNumber());

        return userRepository.save(user);
    }

}