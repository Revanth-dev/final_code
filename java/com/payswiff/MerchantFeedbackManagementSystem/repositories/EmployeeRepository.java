package com.payswiff.MerchantFeedbackManagementSystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payswiff.MerchantFeedbackManagementSystem.models.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findByEmail(String email);
    Employee findByEmailAndPassword(String email, String password);
}