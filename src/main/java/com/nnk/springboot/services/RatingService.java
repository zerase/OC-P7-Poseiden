package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Rating;

public interface RatingService {
	
	Rating addRating(Rating rating);
	
	List<Rating> getAllRatings();
	
	Rating getRatingById(Integer ratingId);
	
	Rating updateRating(Integer ratingId, Rating rating);
	
	void deleteRating(Integer ratingId);
	
}
