package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class BidListController {
	
	private static final Logger logger = LoggerFactory.getLogger(BidListController.class);
	
    // DONE: Inject Bid service
	private final BidListService bidListService;

	@Autowired
	public BidListController(BidListService bidListService) {
		this.bidListService = bidListService;
	}

	
    @GetMapping("/bidList/list")
    public String home(Model model) {
        // DONE: call service find all bids to show to the view
    	logger.info("Request GET for bidList/list");
    	model.addAttribute("bids", bidListService.getAllBidLists());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
    	logger.info("Request GET for bidList/add");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return bid list
    	logger.info("Request POST for bidList/validate");
    	if(result.hasErrors()) {
    		logger.error("Resquest POST for bidList/validate with errors= {}", result);
    		return "bidList/add";
    	}
    	
    	bidListService.addBidList(bid);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Bid by Id and to model then show to the form
    	logger.info("Request GET for bidList/update/{}", id);
    	BidList bidList = bidListService.getBidListById(id);
    	model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Bid and return list Bid
    	logger.info("Request POST for bidList/update/{}", id);
    	if(result.hasErrors()) {
    		model.addAttribute("bidList", bidList);
    		model.addAttribute(id);
    		logger.error("Request POST for bidList/update/{} with errors= {}", id, result);
    		return "bidList/update";
    	}
    	
    	bidListService.updateBidList(id, bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Bid by Id and delete the bid, return to Bid list
    	logger.info("Request GET for bidList/delete/{}", id);
    	bidListService.deleteBidList(id);
        return "redirect:/bidList/list";
    }
}
