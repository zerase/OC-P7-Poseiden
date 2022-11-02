package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

public interface RuleNameService {
	RuleName addRuleName(RuleName ruleName);
	
	List<RuleName> getAllRuleNames();
	
	RuleName getRuleNameById(Integer ruleNameId);
	
	RuleName updateRuleName(Integer ruleNameId, RuleName ruleName);
	
	void deleteRuleName(Integer ruleNameId);
}
