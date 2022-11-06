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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

@SpringBootTest
@WithMockUser(username="user", password="test", roles="USER")
@AutoConfigureMockMvc(addFilters=false)
@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RuleNameControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	RuleNameService ruleNameService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		RuleName ruleName = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		ruleNameService.addRuleName(ruleName);
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	@Test
	@DisplayName("Get request /ruleName/list")
	void getRuleNameTest() throws Exception {
		mockMvc.perform(get("/ruleName/list"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("ruleName/list"))
			.andExpect(content().string(containsString("template")));
	}
	
	@Test
	@DisplayName("Get request /ruleName/add")
	void getRuleNameAddTest() throws Exception {
		mockMvc.perform(get("/ruleName/add"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("ruleName/add"))
			.andExpect(model().attributeExists("ruleName"));
	}

	@Test
	@DisplayName("Post request /ruleName/validate")
	void postNewRuleNameTest() throws Exception {
		RuleName ruleNameToAdd = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		
		mockMvc.perform(post("/ruleName/validate")
				.param("name", ruleNameToAdd.getName())
				.param("description", ruleNameToAdd.getDescription())
				.param("json", ruleNameToAdd.getJson())
				.param("template", ruleNameToAdd.getTemplate())
				.param("sqlStr", ruleNameToAdd.getSqlStr())
				.param("sqlPart", ruleNameToAdd.getSqlPart()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/ruleName/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /ruleName/validate with errors")
	void postNewRuleNameWithErrorsTest() throws Exception {
		RuleName ruleNameToAdd = new RuleName("", "description", "json", "template", "sqlStr", "sqlPart");
		
		mockMvc.perform(post("/ruleName/validate")
				.param("name", ruleNameToAdd.getName())
				.param("description", ruleNameToAdd.getDescription())
				.param("json", ruleNameToAdd.getJson())
				.param("template", ruleNameToAdd.getTemplate())
				.param("sqlStr", ruleNameToAdd.getSqlStr())
				.param("sqlPart", ruleNameToAdd.getSqlPart()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("ruleName/add"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /ruleName/update/{id}")
	void getRuleNameUpdateTest() throws Exception {
		mockMvc.perform(get("/ruleName/update/1"))
			.andExpect(model().attributeExists("ruleName"))
			.andExpect(view().name("ruleName/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Post request /ruleName/update/{id}")
	void postRuleNameToUpdateTest() throws Exception {
		RuleName ruleNameToUpdate = new RuleName("name", "description", "json", "template", "sqlStr", "sqlPart");
		
		mockMvc.perform(post("/ruleName/update/1")
				.param("name", ruleNameToUpdate.getName())
				.param("description", ruleNameToUpdate.getDescription())
				.param("json", ruleNameToUpdate.getJson())
				.param("template", ruleNameToUpdate.getTemplate())
				.param("sqlStr", ruleNameToUpdate.getSqlStr())
				.param("sqlPart", ruleNameToUpdate.getSqlPart()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/ruleName/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /ruleName/update/{id} with errors")
	void postRuleNameToUpdateWithErrorsTest() throws Exception {
		RuleName ruleNameToUpdate = new RuleName("", "description", "json", "template", "sqlStr", "sqlPart");
		
		mockMvc.perform(post("/ruleName/update/1")
				.param("name", ruleNameToUpdate.getName())
				.param("description", ruleNameToUpdate.getDescription())
				.param("json", ruleNameToUpdate.getJson())
				.param("template", ruleNameToUpdate.getTemplate())
				.param("sqlStr", ruleNameToUpdate.getSqlStr())
				.param("sqlPart", ruleNameToUpdate.getSqlPart()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("ruleName/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /ruleName/delete/{id}")
	void getRuleNameToDeleteTest() throws Exception {
		mockMvc.perform(get("/ruleName/delete/1"))
			.andExpect(view().name("redirect:/ruleName/list"))
			.andExpect(status().isFound());
	}

}
