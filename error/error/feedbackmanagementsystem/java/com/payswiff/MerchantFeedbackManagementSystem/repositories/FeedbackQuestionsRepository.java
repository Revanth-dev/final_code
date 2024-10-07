package com.payswiff.MerchantFeedbackManagementSystem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payswiff.MerchantFeedbackManagementSystem.models.FeedbackQuestions;


@Repository
public interface FeedbackQuestionsRepository extends JpaRepository<FeedbackQuestions, Long> {



}
