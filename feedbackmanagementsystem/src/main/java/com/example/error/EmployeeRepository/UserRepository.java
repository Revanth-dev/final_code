package com.example.error.EmployeeRepository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.error.Employee.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Query by email
}