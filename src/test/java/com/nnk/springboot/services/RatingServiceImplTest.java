package com.nnk.springboot.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest {

	@InjectMocks
	RatingServiceImpl ratingService;
	@Mock
	RatingRepository ratingRepository;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	@Test
	@DisplayName("Create new rating")
	void createRatingTest() {
		//GIVEN
		Rating rating =  new Rating();
		rating.setId(1);
		rating.setMoodysRating("MoodysRating Test");
		rating.setSandPRating("SandPRating Test");
		rating.setFitchRating("FitchRating Test");
		rating.setOrderNumber(10);
		
		//WHEN
		ratingService.addRating(rating);
		
		//THEN
		Mockito.verify(ratingRepository, Mockito.times(1)).save(any());
	}

	@Test
	@DisplayName("Get all ratings")
	void getAllRatingsTest() {
		//GIVEN
		Rating rating1 =  new Rating();
		rating1.setId(1);
		rating1.setMoodysRating("MoodysRating1");
		rating1.setSandPRating("SandPRating1");
		rating1.setFitchRating("FitchRating1");
		rating1.setOrderNumber(10);
		
		Rating rating2 =  new Rating();
		rating2.setId(2);
		rating2.setMoodysRating("MoodysRating2");
		rating2.setSandPRating("SandPRating2");
		rating2.setFitchRating("FitchRating2");
		rating2.setOrderNumber(20);
		
		List<Rating> ratingsList = new ArrayList<>();
		ratingsList.add(rating1);
		ratingsList.add(rating2);
		
		Mockito.when(ratingRepository.findAll()).thenReturn(ratingsList);
		
		//WHEN
		List<Rating> allRatings =  ratingService.getAllRatings();
		
		//THEN
		assertEquals(2, allRatings.size());
		assertEquals("MoodysRating1", allRatings.get(0).getMoodysRating());
	}
	
	@Test
	@DisplayName("Get rating by id")
	void getRatingByIdTest() {
		//GIVEN
		Rating rating =  new Rating();
		rating.setId(1);
		rating.setMoodysRating("MoodysRating Test");
		rating.setSandPRating("SandPRating Test");
		rating.setFitchRating("FitchRating Test");
		rating.setOrderNumber(10);
		
		Mockito.when(ratingRepository.findById(anyInt())).thenReturn(Optional.ofNullable(rating));
		
		//WHEN
		Rating actualRating = ratingService.getRatingById(1);
		
		//THEN
		assertEquals("SandPRating Test", actualRating.getSandPRating());
	}
	
	@Test
	@DisplayName("Update a rating")
	void updateRatingTest() {
		//GIVEN
		Rating ratingToUpdate =  new Rating();
		ratingToUpdate.setId(1);
		ratingToUpdate.setMoodysRating("MoodysRating Test");
		ratingToUpdate.setSandPRating("SandPRating Test");
		ratingToUpdate.setFitchRating("FitchRating Test");
		ratingToUpdate.setOrderNumber(10);
		
		Rating updatedRating =  new Rating();
		updatedRating.setId(1);
		updatedRating.setMoodysRating("MoodysRating Updated");
		updatedRating.setSandPRating("SandPRating Updated");
		updatedRating.setFitchRating("FitchRating Updated");
		updatedRating.setOrderNumber(100);
		
		Mockito.when(ratingRepository.findById(anyInt())).thenReturn(Optional.ofNullable(ratingToUpdate));
		Mockito.when(ratingRepository.save(any(Rating.class))).thenReturn(updatedRating);
		
		//WHEN
		Rating ratingSaved = ratingService.updateRating(1, updatedRating);
		
		//THEN
		Mockito.verify(ratingRepository, Mockito.times(1)).save(ratingSaved);
		assertEquals("FitchRating Updated", updatedRating.getFitchRating());
	}
	
	@Test
	@DisplayName("Delete a rating")
	void deleteRatingTest() {
		//GIVEN
		Rating ratingToDelete =  new Rating();
		ratingToDelete.setId(1);
		ratingToDelete.setMoodysRating("MoodysRating Test");
		ratingToDelete.setSandPRating("SandPRating Test");
		ratingToDelete.setFitchRating("FitchRating Test");
		ratingToDelete.setOrderNumber(10);
		
		Mockito.when(ratingRepository.findById(anyInt())).thenReturn(Optional.ofNullable(ratingToDelete));
		
		//WHEN
		ratingService.deleteRating(1);
		
		//THEN
		Mockito.verify(ratingRepository, Mockito.times(1)).delete(ratingToDelete);
	}

}
