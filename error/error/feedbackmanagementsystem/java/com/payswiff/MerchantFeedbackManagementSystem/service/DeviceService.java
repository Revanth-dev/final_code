package com.payswiff.MerchantFeedbackManagementSystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payswiff.MerchantFeedbackManagementSystem.models.Device;
import com.payswiff.MerchantFeedbackManagementSystem.repositories.DeviceRepository;

import java.util.List;

@Service
public class DeviceService {
    
    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(int id) {
        return deviceRepository.findById(id).orElse(null);
    }

    public Device createDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device updateDevice(int id, Device device) {
        if (deviceRepository.existsById(id)) {
            device.setDeviceId(id);
            return deviceRepository.save(device);
        }
        return null;
    }

    public void deleteDevice(int id) {
        deviceRepository.deleteById(id);
    }
}
