package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

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
public class TradeController {
	
	private static final Logger logger = LoggerFactory.getLogger(TradeController.class);
	
    // DONE: Inject Trade service
	private final TradeService tradeService;

	@Autowired
	public TradeController(TradeService tradeService) {
		this.tradeService = tradeService;
	}

	
    @RequestMapping("/trade/list")
    public String home(Model model) {
        // DONE: find all Trade, add to model
    	logger.info("Request GET for trade/list");
    	
    	model.addAttribute("trades", tradeService.getAllTrades());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
    	logger.info("Request GET for trade/add");
        
    	return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Trade list
    	logger.info("Request POST for trade/validate");
    	
    	if(result.hasErrors()) {
    		logger.error("Resquest POST for trade/validate with errors= {}", result);
    		return "trade/add";
    	}
    	
    	tradeService.addTrade(trade);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Trade by Id and to model then show to the form
    	logger.info("Request GET for trade/update/{}", id);
    	
    	Trade trade = tradeService.getTradeById(id);
    	model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Trade and return Trade list
    	logger.info("Request POST for trade/update/{}", id);
    	
    	if(result.hasErrors()) {
    		model.addAttribute("trade", trade);
    		model.addAttribute(id);
    		logger.error("Request POST for trade/update/{} with errors= {}", id, result);
    		return "trade/update";
    	}
    	
    	tradeService.updateTrade(id, trade);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Trade by Id and delete the Trade, return to Trade list
    	logger.info("Request GET for trade/delete/{}", id);
    	
    	tradeService.deleteTrade(id);
        return "redirect:/trade/list";

    }
}
