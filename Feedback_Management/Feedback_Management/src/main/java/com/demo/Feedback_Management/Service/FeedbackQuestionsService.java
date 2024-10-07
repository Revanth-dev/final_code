package com.demo.Feedback_Management.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Feedback_Management.Entity.FeedbackQuestions;
import com.demo.Feedback_Management.Repository.FeedbackQuestionsRepository;

import java.util.List;


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