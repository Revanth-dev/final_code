package com.example.error.AuthenticationController;

import com.example.error.AuthenticationService.AuthenticationService;
import com.example.error.AuthenticationService.JwtService;
import com.example.error.Employee.Employee;
import com.example.error.dto.LoginRequest;
import com.example.error.dto.LoginResponse;
import com.example.error.dto.PasswordResetRequest;
import com.example.error.dto.ResetPasswordRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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


    // New method to request password reset (no changes needed)
    @PostMapping("/request-password-reset")
    public ResponseEntity<String> requestPasswordReset(@RequestBody PasswordResetRequest request) {
        authenticationService.requestPasswordReset(request.getEmail());
        return ResponseEntity.ok("Password reset link has been sent to your email.");
    }

    // Password reset method (no changes needed for token validation)
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        String email = request.getEmail();
        String newPassword = request.getNewPassword();
        String token = request.getToken(); // Include token in the request body
        
        // Validate the token (can add token validation here if needed)
        if (!jwtService.validateToken(token, email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
        }

        authenticationService.resetPassword(email, newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }

    // New method to get a specific user by email (secured endpoint)
    @GetMapping("/user/{email}")
    public ResponseEntity<Employee> getUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Extract the token after "Bearer "
        if (!jwtService.validateToken(token, email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        Employee employee = authenticationService.getUserByEmail(email);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // New method to retrieve all users (secured endpoint)
    @GetMapping("/users")
    public ResponseEntity<List<Employee>> getAllUsers(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Extract the token after "Bearer "
        if (!jwtService.validateToken(token, token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Employee> users = authenticationService.getAllUsers();
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
}
