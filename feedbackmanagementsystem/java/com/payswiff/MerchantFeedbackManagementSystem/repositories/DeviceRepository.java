package com.payswiff.MerchantFeedbackManagementSystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.payswiff.MerchantFeedbackManagementSystem.models.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
