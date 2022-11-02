package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

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
public class CurveController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurveController.class);
    
	// DONE: Inject Curve Point service
	private final CurvePointService curvePointService;
	
	@Autowired
	public CurveController(CurvePointService curvePointService) {
		this.curvePointService = curvePointService;
	}

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        // DONE: find all Curve Point, add to model
    	logger.info("Request GET for curvePoint/list");
    	
    	model.addAttribute("curvePoints", curvePointService.getAllCurvePoints());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
    	logger.info("Request GET for curvePoint/add");
    	
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Curve list
    	logger.info("Request POST for curvePoint/validate");
    	
    	if(result.hasErrors()) {
    		logger.error("Resquest POST for curvePoint/validate with errors= {}", result);
    		return "curvePoint/add";
    	}
    	
    	curvePointService.addCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get CurvePoint by Id and to model then show to the form
    	logger.info("Request GET for curvePoint/update/{}", id);
    	
    	CurvePoint curvePoint = curvePointService.getCurvePointById(id);
    	model.addAttribute("curvePoint", curvePoint);
    	return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Curve and return Curve list
    	logger.info("Request POST for curvePoint/update/{}", id);
    	
    	if(result.hasErrors()) {
    		model.addAttribute("curvePoint", curvePoint);
    		model.addAttribute(id);
    		logger.error("Request POST for curvePoint/update/{} with errors= {}", id, result);
    		return "curvePoint/update";
    	}
    	
    	curvePointService.updateCurvePoint(id, curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Curve by Id and delete the Curve, return to Curve list
    	logger.info("Request GET for curvePoint/delete/{}", id);
    	
    	curvePointService.deleteCurvePoint(id);
        return "redirect:/curvePoint/list";
    }
}
