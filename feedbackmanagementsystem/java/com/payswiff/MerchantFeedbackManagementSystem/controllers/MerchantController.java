package com.payswiff.MerchantFeedbackManagementSystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.payswiff.MerchantFeedbackManagementSystem.models.Merchant;
import com.payswiff.MerchantFeedbackManagementSystem.service.MerchantService;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    // Get all merchants
    @GetMapping
    public List<Merchant> getAllMerchants() {
        return merchantService.getAllMerchants();
    }

    // Get merchant by ID
    @GetMapping("/{id}")
    public Merchant getMerchantById(@PathVariable int id) {
        return merchantService.getMerchantById(id);
    }

    // Create a new merchant
    @PostMapping("/merchant")
    public ResponseEntity<?> createMerchant(@RequestBody Merchant merchant) {
        // Validate that businessName is not null or empty
        if (merchant.getBusinessName() == null || merchant.getBusinessName().isEmpty()) {
            return ResponseEntity.badRequest().body("Business Name cannot be null or empty");
        }

        merchantService.createMerchant(merchant);
        return ResponseEntity.ok("Merchant created successfully");
    }

    // Update an existing merchant
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMerchant(@PathVariable int id, @RequestBody Merchant merchant) {
        Merchant updatedMerchant = merchantService.updateMerchant(id, merchant);
        if (updatedMerchant != null) {
            return ResponseEntity.ok(updatedMerchant);
        }
        return ResponseEntity.badRequest().body("Merchant not found with ID: " + id);
    }

    // Delete a merchant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMerchant(@PathVariable int id) {
        boolean isDeleted = merchantService.deleteMerchant(id);
        if (isDeleted) {
            return ResponseEntity.ok("Merchant deleted successfully");
        }
        return ResponseEntity.badRequest().body("Merchant not found with ID: " + id);
    }
}
