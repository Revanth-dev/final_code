package com.payswiff.MerchantFeedbackManagementSystem.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "feedback_questions")
@Data
public class FeedbackQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feedback_id", nullable = false)
    private Long feedbackId;

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(name = "answer", nullable = false)
    private String answer;

    public Long getFeedbackId() {
        return feedbackId; // Return the actual feedbackId value
    }

    public Long getQuestionId() {
        return questionId; // Return the actual questionId value
    }

    public String getAnswer() {
        return answer; // Return the actual answer value
    
    // Getters and Setters
}
}