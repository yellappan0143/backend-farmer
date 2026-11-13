package com.farmride.farmride_backend.service;



import com.farmride.farmride_backend.dto.LoginRequest;
import com.farmride.farmride_backend.entity.User;
import com.farmride.farmride_backend.repository.UserRepository;
import com.farmride.farmride_backend.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtService = jwtService;
    }

    public String login(LoginRequest request) {

        if (request.getIdentifier() == null || request.getIdentifier().isBlank()) {
            throw new IllegalArgumentException("Email or mobile number is required");
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        String identifier = request.getIdentifier().trim();

        User user = identifier.contains("@")
                ? userRepository.findByEmail(identifier)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid email/mobile number or password."))
                : userRepository.findByMobileNumber(identifier)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid email/mobile number or password."));

        if (!passwordEncoder.matches( request.getPassword(),user.getPassword())) {
            throw new IllegalArgumentException("Invalid password please check!");
        }

        return jwtService.generateToken( user.getEmail() != null ? user.getEmail() : user.getMobileNumber(),user.getRole());
    }
}
