package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

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
public class RuleNameController {
	
	private static final Logger logger = LoggerFactory.getLogger(RuleNameController.class);
	
    // DONE: Inject RuleName service
	private final RuleNameService ruleNameService;

	@Autowired
	public RuleNameController(RuleNameService ruleNameService) {
		this.ruleNameService = ruleNameService;
	}

	
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        // DONE: find all RuleName, add to model
    	logger.info("Request GET for ruleName/list");
    	model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
    	logger.info("Request GET for ruleName/add");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return RuleName list
    	logger.info("Request POST for ruleName/validate");
    	if(result.hasErrors()) {
    		logger.error("Resquest POST for ruleName/validate with errors= {}", result);
    		return "ruleName/add";
    	}
    	
    	ruleNameService.addRuleName(ruleName);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get RuleName by Id and to model then show to the form
    	logger.info("Request GET for ruleName/update/{}", id);
    	RuleName ruleName = ruleNameService.getRuleNameById(id);
    	model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update RuleName and return RuleName list
    	logger.info("Request POST for ruleName/update/{}", id);
    	if(result.hasErrors()) {
    		model.addAttribute("ruleName", ruleName);
    		model.addAttribute(id);
    		logger.error("Request POST for ruleName/update/{} with errors= {}", id, result);
    		return "ruleName/update";
    	}
    	
    	ruleNameService.updateRuleName(id, ruleName);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // DONE: Find RuleName by Id and delete the RuleName, return to Rule list
    	logger.info("Request GET for ruleName/delete/{}", id);
    	ruleNameService.deleteRuleName(id);
        return "redirect:/ruleName/list";
    }
}
