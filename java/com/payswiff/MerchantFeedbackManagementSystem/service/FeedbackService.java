package com.payswiff.MerchantFeedbackManagementSystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payswiff.MerchantFeedbackManagementSystem.models.Feedback;
import com.payswiff.MerchantFeedbackManagementSystem.repositories.FeedbackRepository;



@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback createFeedback(Feedback feedback) {
        feedback.setCreatedAt(LocalDateTime.now());  // Set the timestamp
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> getFeedbackById(int feedbackId) {
        return feedbackRepository.findById(feedbackId);
    }

    public Double getAverageRating() {
        Double averageRating = feedbackRepository.findAverageRating();
        return averageRating != null ? averageRating : 0.0;
    }
}
