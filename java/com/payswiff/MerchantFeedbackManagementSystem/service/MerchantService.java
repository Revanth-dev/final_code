package com.payswiff.MerchantFeedbackManagementSystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payswiff.MerchantFeedbackManagementSystem.models.Merchant;
import com.payswiff.MerchantFeedbackManagementSystem.repositories.MerchantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService {
    
    @Autowired
    private MerchantRepository merchantRepository;

    // Get all merchants
    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    // Get merchant by ID
    public Merchant getMerchantById(int id) {
        Optional<Merchant> merchant = merchantRepository.findById(id);
        return merchant.orElse(null);  // Return null if merchant not found
    }

    // Create a new merchant
    public void createMerchant(Merchant merchant) {
        // Validation check for business name
        if (merchant.getBusinessName() == null || merchant.getBusinessName().isEmpty()) {
            throw new IllegalArgumentException("Business Name cannot be null or empty");
        }
        merchantRepository.save(merchant);  // Save the new merchant
    }

    // Update an existing merchant
    public Merchant updateMerchant(int id, Merchant merchant) {
        // Check if merchant with the given ID exists
        if (merchantRepository.existsById(id)) {
            merchant.setMerchant_id(id);  // Ensure the correct ID is set for update
            return merchantRepository.save(merchant);  // Save the updated merchant
        }
        return null;  // Return null if merchant not found
    }

    // Delete a merchant by ID
    public boolean deleteMerchant(int id) {
        if (merchantRepository.existsById(id)) {
            merchantRepository.deleteById(id);  // Delete the merchant
            return true;
        }
        return false;  // Return false if merchant not found
    }
}
