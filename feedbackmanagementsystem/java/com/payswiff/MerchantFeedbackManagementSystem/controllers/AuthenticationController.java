package com.payswiff.MerchantFeedbackManagementSystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.payswiff.MerchantFeedbackManagementSystem.dto.LoginRequest;
import com.payswiff.MerchantFeedbackManagementSystem.dto.LoginResponse;
import com.payswiff.MerchantFeedbackManagementSystem.models.Employee;
import com.payswiff.MerchantFeedbackManagementSystem.service.AuthenticationService;
import com.payswiff.MerchantFeedbackManagementSystem.service.JwtService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private JwtService jwtService; // For generating and validating JWTs

    // Sign-up method (no changes needed)
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee) {
        try {
            authenticationService.register(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login Request: " + loginRequest); // Log the incoming request

        // Check for null or empty fields in the login request
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty() || 
            loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body(new LoginResponse("Email and password must be provided."));
        }

        // Attempt to authenticate the user
        try {
            Employee employee = authenticationService.getUserByEmail(loginRequest.getEmail());
            if (employee != null && authenticationService.passwordMatches(loginRequest.getPassword(), employee.getPassword())) {
                // Generate JWT token
                String token = jwtService.generateToken(employee.getEmail());
                return ResponseEntity.ok(new LoginResponse(token, employee.getEmail()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid email or password."));
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid email or password."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponse("An error occurred during authentication."));
        }
    }
}