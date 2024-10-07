package com.payswiff.MerchantFeedbackManagementSystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.payswiff.MerchantFeedbackManagementSystem.models.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
}



