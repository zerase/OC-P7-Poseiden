package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {
	
	private static final Logger logger = LoggerFactory.getLogger(RatingService.class);
	
	private final RatingRepository ratingRepository;
	
	@Autowired
	public RatingServiceImpl(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}

	
	// ======= CREATE =========================================================
	/**
	 * Add a new rating
	 * 
	 * @param rating The new rating to add
	 * @return the saved rating
	 */
	@Override
	public Rating addRating(Rating rating) {
		logger.info("Call to service that add a new rating");
		
		Rating ratingToAdd = ratingRepository.save(rating);
		logger.info("New rating {} is created sucessfully", rating);
		return ratingToAdd;
	}

	// ======= READ ALL =======================================================
	/**
	 * Get all ratings
	 * 
	 * @return the list of all ratings
	 */
	@Override
	public List<Rating> getAllRatings() {
		logger.info("Call to service that get list of all ratings");
		
		List<Rating> allRatings = ratingRepository.findAll();
		
		if(allRatings.isEmpty()) {
			logger.info("No rating in database");
			return new ArrayList<>();
		}
		
		logger.info("Retrieve list of all rating successfully");
		return allRatings;
	}

	// ======= READ ===========================================================
	/**
	 * Get a rating by id
	 * 
	 * @param ratingId The id of the rating to find
	 * @return The rating with the corresponding id requested
	 */
	@Override
	public Rating getRatingById(Integer ratingId) {
		logger.info("Call to service that get a rating by id");
		
		Rating existingRating = ratingRepository.findById(ratingId).orElseThrow(() -> {
			logger.error("Failed to retrieve rating with id {}", ratingId);
			throw new IllegalArgumentException("Invalid rating Id : " + ratingId);
		});
		logger.info("Retrieve rating by id successfully");
		return existingRating;
	}

	// ======= UPDATE =========================================================
	/**
	 * Update an existing rating
	 * 
	 * @param ratingId The id of the requested rating
	 * @param rating The rating to update
	 * @return The updated rating
	 */
	@Override
	public Rating updateRating(Integer ratingId, Rating rating) {
		logger.info("Call to service that update an existing rating");
		
		ratingRepository.findById(ratingId).orElseThrow(() -> {
			logger.error("Failed to retrieve rating with id {}", ratingId);
			throw new IllegalArgumentException("Invalid rating Id : " + ratingId);
		});
		rating.setId(ratingId);
		Rating updatedRating = ratingRepository.save(rating);
		logger.info("Updated rating successfully");
		return updatedRating;
	}

	// ======= DELETE =========================================================
	/**
	 * Delete a rating
	 * 
	 * @param ratingId The id of the rating to delete
	 */
	@Override
	public void deleteRating(Integer ratingId) {
		logger.info("Call to service that delete a rating");
		
		Optional<Rating> ratingToDelete = ratingRepository.findById(ratingId);
		if(ratingToDelete.isPresent()) {
			ratingRepository.delete(ratingToDelete.get());
			logger.info("Rating with id {} deleted successfully", ratingId);
		} else {
			logger.error("Failed to delete rating with id {}", ratingId);
		}
	}

}
