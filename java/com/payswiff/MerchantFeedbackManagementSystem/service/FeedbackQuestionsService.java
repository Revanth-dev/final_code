package com.payswiff.MerchantFeedbackManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payswiff.MerchantFeedbackManagementSystem.models.FeedbackQuestions;
import com.payswiff.MerchantFeedbackManagementSystem.repositories.FeedbackQuestionsRepository;


@Service
public class FeedbackQuestionsService {

    @Autowired
    private FeedbackQuestionsRepository feedbackQuestionsRepository;

    public FeedbackQuestions saveFeedbackQuestion(FeedbackQuestions feedbackQuestion) {
        if (feedbackQuestion.getAnswer() == null || feedbackQuestion.getFeedbackId() == null || feedbackQuestion.getQuestionId() == null) {
            throw new IllegalArgumentException("Feedback ID, Question ID, and Answer must not be null");
        }
        return feedbackQuestionsRepository.save(feedbackQuestion);
    }


    public List<FeedbackQuestions> getAllFeedbackQuestions() {
        return feedbackQuestionsRepository.findAll();
    }
}