package com.nnk.springboot.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

@SpringBootTest
@WithMockUser(username="user", password="test", roles="USER")
@AutoConfigureMockMvc(addFilters=false)
@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RatingControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	RatingService ratingService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Rating rating = new Rating("moodysRating", "sandPRating", "fitchRating", 123);
		ratingService.addRating(rating);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	@DisplayName("Get request /rating/list")
	void getRatingTest() throws Exception {
		mockMvc.perform(get("/rating/list"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("rating/list"))
			.andExpect(content().string(containsString("moodysRating")));
	}
	
	@Test
	@DisplayName("Get request /rating/add")
	void getRatingAddTest() throws Exception {
		mockMvc.perform(get("/rating/add"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("rating/add"))
			.andExpect(model().attributeExists("rating"));
	}

	@Test
	@DisplayName("Post request /rating/validate")
	void postNewRatingTest() throws Exception {
		Rating ratingToAdd = new Rating("moodysRatingTest", "sandPRatingTest", "fitchRatingTest", 123);
		
		mockMvc.perform(post("/rating/validate")
				.param("moodysRating", ratingToAdd.getMoodysRating())
				.param("sandPRating", ratingToAdd.getSandPRating())
				.param("fitchRating", ratingToAdd.getFitchRating())
				.param("orderNumber", ratingToAdd.getOrderNumber().toString()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/rating/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /rating/validate with errors")
	void postNewRatingWithErrorsTest() throws Exception {
		Rating ratingToAdd = new Rating("", "sandPRatingTest", "fitchRatingTest", 123);
		
		mockMvc.perform(post("/rating/validate")
				.param("moodysRating", ratingToAdd.getMoodysRating())
				.param("sandPRating", ratingToAdd.getSandPRating())
				.param("fitchRating", ratingToAdd.getFitchRating())
				.param("orderNumber", ratingToAdd.getOrderNumber().toString()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("rating/add"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /rating/update/{id}")
	void getRatingUpdateTest() throws Exception {
		mockMvc.perform(get("/rating/update/1"))
			.andExpect(model().attributeExists("rating"))
			.andExpect(view().name("rating/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Post request /rating/update/{id}")
	void postRatingToUpdateTest() throws Exception {
		Rating ratingToUpdate = new Rating("moodysRatingUpdated", "sandPRatingUpdated", "fitchRatingUpdated", 123);
		
		mockMvc.perform(post("/rating/update/1")
				.param("moodysRating", ratingToUpdate.getMoodysRating())
				.param("sandPRating", ratingToUpdate.getSandPRating())
				.param("fitchRating", ratingToUpdate.getFitchRating())
				.param("orderNumber", ratingToUpdate.getOrderNumber().toString()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/rating/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /rating/update/{id} with errors")
	void postRatingToUpdateWithErrorsTest() throws Exception {
		Rating ratingToUpdate = new Rating("", "sandPRating", "fitchRating", 123);
		
		mockMvc.perform(post("/rating/update/1")
				.param("moodysRating", ratingToUpdate.getMoodysRating())
				.param("sandPRating", ratingToUpdate.getSandPRating())
				.param("fitchRating", ratingToUpdate.getFitchRating())
				.param("orderNumber", ratingToUpdate.getOrderNumber().toString()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("rating/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /rating/delete/{id}")
	void getRatingToDeleteTest() throws Exception {
		mockMvc.perform(get("/rating/delete/1"))
			.andExpect(view().name("redirect:/rating/list"))
			.andExpect(status().isFound());
	}
	
}
