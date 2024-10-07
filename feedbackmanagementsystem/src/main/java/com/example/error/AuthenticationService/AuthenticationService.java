package com.example.error.AuthenticationService;

import java.util.List;
import java.util.UUID;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.error.Employee.Employee;
import com.example.error.Employee.PasswordResetToken;
import com.example.error.EmployeeRepository.EmployeeRepository;
import com.example.error.EmployeeRepository.PasswordResetTokenRepository;
import com.example.error.dto.LoginResponse;


@Service
public class AuthenticationService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository; // Inject the repository

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Employee register(Employee employee) {
        // Hash the password before saving
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public LoginResponse login(String email, String password) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee != null && passwordEncoder.matches(password, employee.getPassword())) {
            // Generate JWT token upon successful login
            String token = jwtService.generateToken(employee.getEmail());
            return new LoginResponse("Login successful", token); // Return token with success message
        }
        return null; // Authentication failed
    }

    public Employee getUserByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        // Check if employee is null and throw exception if not found
        if (employee == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return employee;
    }

    public List<Employee> getAllUsers() {
        // Retrieve all employees
        return employeeRepository.findAll();
    }

    // New method to handle password reset requests
    public void requestPasswordReset(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee != null) {
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setEmail(email);
            resetToken.setExpiryDate(new Date(System.currentTimeMillis() + 3600000)); // 1 hour expiry
            passwordResetTokenRepository.save(resetToken);
            // Send email with reset link (implement the email sending logic)
            sendPasswordResetEmail(email, token);
        }
    }

    // New method to reset password
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        if (resetToken != null && !isTokenExpired(resetToken)) {
            Employee employee = employeeRepository.findByEmail(resetToken.getEmail());
            employee.setPassword(passwordEncoder.encode(newPassword));
            employeeRepository.save(employee);
            passwordResetTokenRepository.delete(resetToken); // Remove token after successful reset
        } else {
            throw new IllegalArgumentException("Invalid token or token has expired");
        }
    }

    private boolean isTokenExpired(PasswordResetToken token) {
        return token.getExpiryDate().before(new Date());
    }

    private void sendPasswordResetEmail(String email, String token) {
        // Implement email sending logic (use JavaMailSender)
    }

    public boolean passwordMatches(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}