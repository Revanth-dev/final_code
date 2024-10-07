package com.payswiff.MerchantFeedbackManagementSystem.dto;


public class LoginRequest {
    private String email;
    private String password;

    // Getter for email
    public String getEmail() {
        return email; // Return the actual email value
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password; // Return the actual password value
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

