package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
@Transactional
public class RuleNameServiceImpl implements RuleNameService {
	
	private static final Logger logger = LoggerFactory.getLogger(RuleNameService.class);
	
	private final RuleNameRepository ruleNameRepository;
	
	@Autowired
	public RuleNameServiceImpl(RuleNameRepository ruleNameRepository) {
		this.ruleNameRepository = ruleNameRepository;
	}

	
	// ======= CREATE =========================================================
	/**
	 * Add a new ruleName
	 * 
	 * @param ruleName The new ruleName to add
	 * @return the saved ruleName
	 */
	@Override
	public RuleName addRuleName(RuleName ruleName) {
		logger.info("Call to service that add a new ruleName");
		RuleName ruleNameToAdd = ruleNameRepository.save(ruleName);
		logger.info("New ruleName {} is created sucessfully", ruleName);
		return ruleNameToAdd;
	}

	// ======= READ ALL =======================================================
	/**
	 * Get all ruleNames
	 * 
	 * @return the list of all ruleNames
	 */
	@Override
	public List<RuleName> getAllRuleNames() {
		logger.info("Call to service that get list of all ruleNames");
		List<RuleName> allRuleNames = ruleNameRepository.findAll();
		
		if(allRuleNames.isEmpty()) {
			logger.info("No ruleName in database");
			return new ArrayList<>();
		}
		
		logger.info("Retrieve list of all ruleName successfully");
		return allRuleNames;
	}

	// ======= READ ===========================================================
	/**
	 * Get a ruleName by id
	 * 
	 * @param ruleNameId The id of the ruleName to find
	 * @return The ruleName with the corresponding id requested
	 */
	@Override
	public RuleName getRuleNameById(Integer ruleNameId) {
		logger.info("Call to service that get a ruleName by id");
		RuleName existingRuleName = ruleNameRepository.findById(ruleNameId).orElseThrow(() -> {
			logger.error("Failed to retrieve ruleName with id {}", ruleNameId);
			throw new IllegalArgumentException("Invalid ruleName Id : " + ruleNameId);
		});
		logger.info("Retrieve ruleName by id successfully");
		return existingRuleName;
	}

	// ======= UPDATE =========================================================
	/**
	 * Update an existing ruleName
	 * 
	 * @param ruleNameId The id of the requested ruleName
	 * @param ruleName The ruleName to update
	 * @return The updated ruleName
	 */
	@Override
	public RuleName updateRuleName(Integer ruleNameId, RuleName ruleName) {
		logger.info("Call to service that update an existing ruleName");
		ruleNameRepository.findById(ruleNameId).orElseThrow(() -> {
			logger.error("Failed to retrieve ruleName with id {}", ruleNameId);
			throw new IllegalArgumentException("Invalid ruleName Id : " + ruleNameId);
		});
		ruleName.setId(ruleNameId);
		RuleName updatedRuleName = ruleNameRepository.save(ruleName);
		logger.info("Updated ruleName successfully");
		return updatedRuleName;
	}

	// ======= DELETE =========================================================
	/**
	 * Delete a ruleName
	 * 
	 * @param ruleNameId The id of the ruleName to delete
	 */
	@Override
	public void deleteRuleName(Integer ruleNameId) {
		logger.info("Call to service that delete a ruleName");
		Optional<RuleName> ruleNameToDelete = ruleNameRepository.findById(ruleNameId);
		if(ruleNameToDelete.isPresent()) {
			ruleNameRepository.delete(ruleNameToDelete.get());
			logger.info("RuleName with id {} deleted successfully", ruleNameId);
		} else {
			logger.error("Failed to delete ruleName with id {}", ruleNameId);
		}
	}

}
