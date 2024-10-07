package com.demo.Feedback_Management.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Feedback_Management.Entity.FeedbackQuestions;
@Repository
public interface FeedbackQuestionsRepository extends JpaRepository<FeedbackQuestions, Long> {



}
