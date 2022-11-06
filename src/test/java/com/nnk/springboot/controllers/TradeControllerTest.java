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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

@SpringBootTest
@WithMockUser(username="user", password="test", roles="USER")
@AutoConfigureMockMvc(addFilters=false)
@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TradeControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	TradeService tradeService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Trade trade = new Trade("account test", "type test", 10.0);
		tradeService.addTrade(trade);
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	@Test
	@DisplayName("Get request /trade/list")
	void getTradeTest() throws Exception {
		mockMvc.perform(get("/trade/list"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("trade/list"))
			.andExpect(content().string(containsString("account test")));
	}
	
	@Test
	@DisplayName("Get request /trade/add")
	void getTradeAddTest() throws Exception {
		mockMvc.perform(get("/trade/add"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("trade/add"))
			.andExpect(model().attributeExists("trade"));
	}

	@Test
	@DisplayName("Post request /trade/validate")
	void postNewTradeTest() throws Exception {
		Trade tradeToAdd = new Trade("accountTest", "typeTest", 10.0);
		
		mockMvc.perform(post("/trade/validate")
				.param("account", tradeToAdd.getAccount())
				.param("type", tradeToAdd.getType())
				.param("bidQuantity", tradeToAdd.getBuyQuantity().toString()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/trade/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /trade/validate with errors")
	void postNewTradeWithErrorsTest() throws Exception {
		Trade tradeToAdd = new Trade("", "typeTest", 10.0);
		
		mockMvc.perform(post("/trade/validate")
				.param("account", tradeToAdd.getAccount())
				.param("type", tradeToAdd.getType())
				.param("bidQuantity", tradeToAdd.getBuyQuantity().toString()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("trade/add"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /trade/update/{id}")
	void getTradeUpdateTest() throws Exception {
		mockMvc.perform(get("/trade/update/1"))
			.andExpect(model().attributeExists("trade"))
			.andExpect(view().name("trade/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Post request /trade/update/{id}")
	void postTradeToUpdateTest() throws Exception {
		Trade tradeToUpdate = new Trade("accountTestUpdated", "typeTestUpdated", 100.0);
		
		mockMvc.perform(post("/trade/update/1")
				.param("account", tradeToUpdate.getAccount())
				.param("type", tradeToUpdate.getType())
				.param("bidQuantity", tradeToUpdate.getBuyQuantity().toString()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/trade/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /trade/update/{id} with errors")
	void postTradeToUpdateWithErrorsTest() throws Exception {
		Trade tradeToUpdate = new Trade("", "typeTest", 100.0);
		
		mockMvc.perform(post("/trade/update/1")
				.param("account", tradeToUpdate.getAccount())
				.param("type", tradeToUpdate.getType())
				.param("bidQuantity", tradeToUpdate.getBuyQuantity().toString()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("trade/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /trade/delete/{id}")
	void getTradeToDeleteTest() throws Exception {
		mockMvc.perform(get("/trade/delete/1"))
			.andExpect(view().name("redirect:/trade/list"))
			.andExpect(status().isFound());
	}

}
