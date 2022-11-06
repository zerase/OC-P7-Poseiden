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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@ExtendWith(MockitoExtension.class)
class RuleNameServiceImplTest {

	@InjectMocks
	RuleNameServiceImpl ruleNameService;
	@Mock
	RuleNameRepository ruleNameRepository;


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
	@DisplayName("Create new ruleName")
	void createRuleNameTest() {
		//GIVEN
		RuleName ruleName =  new RuleName();
		ruleName.setId(1);
		ruleName.setName("NameTest");
		ruleName.setDescription("DescriptionTest");
		ruleName.setJson("JsonTest");
		ruleName.setTemplate("TemplateTest");
		ruleName.setSqlStr("SqlStrTest");
		ruleName.setSqlPart("SqlPartTest");
		
		//WHEN
		ruleNameService.addRuleName(ruleName);
		
		//THEN
		Mockito.verify(ruleNameRepository, Mockito.times(1)).save(any());
	}

	@Test
	@DisplayName("Get all ruleNames")
	void getAllRuleNamesTest() {
		//GIVEN
		RuleName ruleName1 =  new RuleName();
		ruleName1.setId(1);
		ruleName1.setName("NameTest1");
		ruleName1.setDescription("DescriptionTest1");
		ruleName1.setJson("JsonTest1");
		ruleName1.setTemplate("TemplateTest1");
		ruleName1.setSqlStr("SqlStrTest1");
		ruleName1.setSqlPart("SqlPartTest1");
		
		RuleName ruleName2 =  new RuleName();
		ruleName2.setId(2);
		ruleName2.setName("NameTest2");
		ruleName2.setDescription("DescriptionTest2");
		ruleName2.setJson("JsonTest2");
		ruleName2.setTemplate("TemplateTest2");
		ruleName2.setSqlStr("SqlStrTest2");
		ruleName2.setSqlPart("SqlPartTest2");
		
		List<RuleName> ruleNamesList = new ArrayList<>();
		ruleNamesList.add(ruleName1);
		ruleNamesList.add(ruleName2);
		
		Mockito.when(ruleNameRepository.findAll()).thenReturn(ruleNamesList);
		
		//WHEN
		List<RuleName> allRuleNames =  ruleNameService.getAllRuleNames();
		
		//THEN
		assertEquals(2, allRuleNames.size());
		assertEquals("NameTest1", allRuleNames.get(0).getName());
	}
	
	@Test
	@DisplayName("Get ruleName by id")
	void getRuleNameByIdTest() {
		//GIVEN
		RuleName ruleName =  new RuleName();
		ruleName.setId(1);
		ruleName.setName("NameTest");
		ruleName.setDescription("DescriptionTest");
		ruleName.setJson("JsonTest");
		ruleName.setTemplate("TemplateTest");
		ruleName.setSqlStr("SqlStrTest");
		ruleName.setSqlPart("SqlPartTest");
		
		Mockito.when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.ofNullable(ruleName));
		
		//WHEN
		RuleName actualRuleName = ruleNameService.getRuleNameById(1);
		
		//THEN
		assertEquals("DescriptionTest", actualRuleName.getDescription());
	}
	
	@Test
	@DisplayName("Update a ruleName")
	void updateRuleNameTest() {
		//GIVEN
		RuleName ruleNameToUpdate =  new RuleName();
		ruleNameToUpdate.setId(1);
		ruleNameToUpdate.setName("NameTest");
		ruleNameToUpdate.setDescription("DescriptionTest");
		ruleNameToUpdate.setJson("JsonTest");
		ruleNameToUpdate.setTemplate("TemplateTest");
		ruleNameToUpdate.setSqlStr("SqlStrTest");
		ruleNameToUpdate.setSqlPart("SqlPartTest");
		
		RuleName updatedRuleName =  new RuleName();
		updatedRuleName.setId(1);
		updatedRuleName.setName("NameUpdated");
		updatedRuleName.setDescription("DescriptionUpdated");
		updatedRuleName.setJson("JsonUpdated");
		updatedRuleName.setTemplate("TemplateUpdated");
		updatedRuleName.setSqlStr("SqlStrUpdated");
		updatedRuleName.setSqlPart("SqlPartUpdated");
		
		
		Mockito.when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.ofNullable(ruleNameToUpdate));
		Mockito.when(ruleNameRepository.save(any(RuleName.class))).thenReturn(updatedRuleName);
		
		//WHEN
		RuleName ruleNameSaved = ruleNameService.updateRuleName(1, updatedRuleName);
		
		//THEN
		Mockito.verify(ruleNameRepository, Mockito.times(1)).save(ruleNameSaved);
		assertEquals("JsonUpdated", updatedRuleName.getJson());
	}
	
	@Test
	@DisplayName("Delete a ruleName")
	void deleteRuleNameTest() {
		//GIVEN
		RuleName ruleNameToDelete =  new RuleName();
		ruleNameToDelete.setId(1);
		ruleNameToDelete.setName("NameTest");
		ruleNameToDelete.setDescription("DescriptionTest");
		ruleNameToDelete.setJson("JsonTest");
		ruleNameToDelete.setTemplate("TemplateTest");
		ruleNameToDelete.setSqlStr("SqlStrTest");
		ruleNameToDelete.setSqlPart("SqlPartTest");
		
		Mockito.when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.ofNullable(ruleNameToDelete));
		
		//WHEN
		ruleNameService.deleteRuleName(1);
		
		//THEN
		Mockito.verify(ruleNameRepository, Mockito.times(1)).delete(ruleNameToDelete);
	}

}
