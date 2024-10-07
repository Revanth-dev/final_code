package com.demo.Feedback_Management.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;

    private int employeeId;
    private int merchantId;
    private int deviceId;

    // Use 'double' for ratings if decimals are allowed, otherwise 'int' for whole numbers
    private double rating;

    @Column(columnDefinition = "json")
    private String feedbackData; // Storing feedback in JSON format
 // Storing multiple image URLs in JSON format

    private String imageUrl;  // Primary image URL or thumbnail

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt; // Timestamp for feedback creation
    
    
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();  // Ensure createdAt is set before saving
    }


	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getFeedbackData() {
		return feedbackData;
	}

	public void setFeedbackData(String feedbackData) {
		this.feedbackData = feedbackData;
	}

	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt; 
    }




		
	}
