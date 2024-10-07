package com.payswiff.MerchantFeedbackManagementSystem.dto;

public class LoginResponse {
    private String token;
    private String email;
    private String errorMessage;

    public LoginResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }
    public LoginResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}