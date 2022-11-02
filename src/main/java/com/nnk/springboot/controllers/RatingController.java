package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController {
	private static final Logger logger = LoggerFactory.getLogger(RatingController.class);
	
    // DONE: Inject Rating service
	private final RatingService ratingService;

	@Autowired
	public RatingController(RatingService ratingService) {
		this.ratingService = ratingService;
	}

    @RequestMapping("/rating/list")
    public String home(Model model) {
        // DONE: find all Rating, add to model
    	logger.info("Request GET for rating/list");
    	
    	model.addAttribute("ratings", ratingService.getAllRatings());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
    	logger.info("Request GET for rating/add");
        
    	return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Rating list
    	logger.info("Request POST for rating/validate");
    	
    	if(result.hasErrors()) {
    		logger.error("Resquest POST for rating/validate with errors= {}", result);
    		return "rating/add";
    	}
    	
    	ratingService.addRating(rating);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Rating by Id and to model then show to the form
    	logger.info("Request GET for rating/update/{}", id);
    	
    	Rating rating = ratingService.getRatingById(id);
    	model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Rating and return Rating list
    	logger.info("Request POST for rating/update/{}", id);
    	if(result.hasErrors()) {
    		model.addAttribute("rating", rating);
    		model.addAttribute(id);
    		logger.error("Request POST for rating/update/{} with errors= {}", id, result);
    		return "rating/update";
    	}
    	
    	ratingService.updateRating(id, rating);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Rating by Id and delete the Rating, return to Rating list
    	logger.info("Request GET for rating/delete/{}", id);
    	
    	ratingService.deleteRating(id);
        return "redirect:/rating/list";
    }
}
