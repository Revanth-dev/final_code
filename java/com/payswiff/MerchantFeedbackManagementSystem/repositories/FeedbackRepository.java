package com.payswiff.MerchantFeedbackManagementSystem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payswiff.MerchantFeedbackManagementSystem.models.Feedback;



@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

	List<Feedback> findAll();
	   @Query("SELECT AVG(f.rating) FROM Feedback f")
	    Double findAverageRating();





}
