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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@SpringBootTest
@WithMockUser(username="user", password="test", roles="USER")
@AutoConfigureMockMvc(addFilters=false)
@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BidListControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	BidListService bidListService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		BidList bidList = new BidList("account test", "type test", 10.0);
		bidListService.addBidList(bidList);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Get request /bidList/list")
	void getBidListTest() throws Exception {
		mockMvc.perform(get("/bidList/list"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("bidList/list"))
			.andExpect(content().string(containsString("account test")));
	}
	
	@Test
	@DisplayName("Get request /bidList/add")
	void getBidListAddTest() throws Exception {
		mockMvc.perform(get("/bidList/add"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("bidList/add"))
			.andExpect(model().attributeExists("bidList"));
	}

	@Test
	@DisplayName("Post request /bidList/validate")
	void postNewBidListTest() throws Exception {
		BidList bidListToAdd = new BidList("accountTest", "typeTest", 10.0);
		
		mockMvc.perform(post("/bidList/validate")
				.param("account", bidListToAdd.getAccount())
				.param("type", bidListToAdd.getType())
				.param("bidQuantity", bidListToAdd.getBidQuantity().toString()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/bidList/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /bidList/validate with errors")
	void postNewBidListWithErrorsTest() throws Exception {
		BidList bidListToAdd = new BidList("", "typeTest", 10.0);
		
		mockMvc.perform(post("/bidList/validate")
				.param("account", bidListToAdd.getAccount())
				.param("type", bidListToAdd.getType())
				.param("bidQuantity", bidListToAdd.getBidQuantity().toString()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("bidList/add"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /bidList/update/{id}")
	void getBidListUpdateTest() throws Exception {
		mockMvc.perform(get("/bidList/update/1"))
			.andExpect(model().attributeExists("bidList"))
			.andExpect(view().name("bidList/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Post request /bidList/update/{id}")
	void postBidListToUpdateTest() throws Exception {
		BidList bidListToUpdate = new BidList("accountTestUpdated", "typeTestUpdated", 100.0);
		
		mockMvc.perform(post("/bidList/update/1")
				.param("account", bidListToUpdate.getAccount())
				.param("type", bidListToUpdate.getType())
				.param("bidQuantity", bidListToUpdate.getBidQuantity().toString()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/bidList/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /bidList/update/{id} with errors")
	void postBidListToUpdateWithErrorsTest() throws Exception {
		BidList bidListToUpdate = new BidList("", "typeTest", 100.0);
		
		mockMvc.perform(post("/bidList/update/1")
				.param("account", bidListToUpdate.getAccount())
				.param("type", bidListToUpdate.getType())
				.param("bidQuantity", bidListToUpdate.getBidQuantity().toString()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("bidList/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /bidList/delete/{id}")
	void getBidListToDeleteTest() throws Exception {
		mockMvc.perform(get("/bidList/delete/1"))
			.andExpect(view().name("redirect:/bidList/list"))
			.andExpect(status().isFound());
	}
}
