package com.payswiff.MerchantFeedbackManagementSystem.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.payswiff.MerchantFeedbackManagementSystem.models.FeedbackQuestions;
import com.payswiff.MerchantFeedbackManagementSystem.service.FeedbackQuestionsService;

import java.util.List;

@RestController
@RequestMapping("/feedback/questions")
public class FeedbackQuestionsController {
    private final FeedbackQuestionsService feedbackQuestionsService;

    @Autowired
    public FeedbackQuestionsController(FeedbackQuestionsService feedbackQuestionsService) {
        this.feedbackQuestionsService = feedbackQuestionsService;
    }

    @PostMapping("/sub")
    public ResponseEntity<FeedbackQuestions> addFeedbackQuestion(@RequestBody FeedbackQuestions feedbackQuestion) {
        FeedbackQuestions savedQuestion = feedbackQuestionsService.saveFeedbackQuestion(feedbackQuestion);
        return ResponseEntity.ok(savedQuestion);
    }


    @GetMapping("/subm")
    public ResponseEntity<List<FeedbackQuestions>> getAllFeedbackQuestions() {
        List<FeedbackQuestions> questionsList = feedbackQuestionsService.getAllFeedbackQuestions();
        return ResponseEntity.ok(questionsList);
    }
    
}
