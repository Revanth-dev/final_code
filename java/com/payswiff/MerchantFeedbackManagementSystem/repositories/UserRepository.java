package com.payswiff.MerchantFeedbackManagementSystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.payswiff.MerchantFeedbackManagementSystem.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Query by email
}