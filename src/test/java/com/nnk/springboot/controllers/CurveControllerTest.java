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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

@SpringBootTest
@WithMockUser(username="user", password="test", roles="USER")
@AutoConfigureMockMvc(addFilters=false)
@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CurveControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	CurvePointService curvePointService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		CurvePoint curvePoint = new CurvePoint(10, 10.0, 10.0);
		curvePointService.addCurvePoint(curvePoint);
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	@Test
	@DisplayName("Get request /curvePoint/list")
	void getCurvePointTest() throws Exception {
		mockMvc.perform(get("/curvePoint/list"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("curvePoint/list"))
			.andExpect(content().string(containsString("10")));
	}
	
	@Test
	@DisplayName("Get request /curvePoint/add")
	void getCurvePointAddTest() throws Exception {
		mockMvc.perform(get("/curvePoint/add"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("curvePoint/add"))
			.andExpect(model().attributeExists("curvePoint"));
	}

	@Test
	@DisplayName("Post request /curvePoint/validate")
	void postNewCurvePointTest() throws Exception {
		CurvePoint curvePointToAdd = new CurvePoint(10, 10.0, 10.0);
		
		mockMvc.perform(post("/curvePoint/validate")
				.param("curveId", curvePointToAdd.getCurveId().toString())
				.param("term", curvePointToAdd.getTerm().toString())
				.param("value", curvePointToAdd.getValue().toString()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/curvePoint/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /curvePoint/validate with errors")
	void postNewCurvePointWithErrorsTest() throws Exception {
		CurvePoint curvePointToAdd = new CurvePoint();
		curvePointToAdd.setTerm(10.0);
		curvePointToAdd.setValue(10.0);
		
		mockMvc.perform(post("/curvePoint/validate")
				.param("term", curvePointToAdd.getTerm().toString())
				.param("value", curvePointToAdd.getValue().toString()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("curvePoint/add"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /curvePoint/update/{id}")
	void getCurvePointUpdateTest() throws Exception {
		mockMvc.perform(get("/curvePoint/update/1"))
			.andExpect(model().attributeExists("curvePoint"))
			.andExpect(view().name("curvePoint/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Post request /curvePoint/update/{id}")
	void postCurvePointToUpdateTest() throws Exception {
		CurvePoint curvePointToUpdate = new CurvePoint(110, 100.0, 100.0);
		
		mockMvc.perform(post("/curvePoint/update/1")
				.param("curveId", curvePointToUpdate.getCurveId().toString())
				.param("term", curvePointToUpdate.getTerm().toString())
				.param("value", curvePointToUpdate.getValue().toString()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/curvePoint/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /curvePoint/update/{id} with errors")
	void postCurvePointToUpdateWithErrorsTest() throws Exception {
		CurvePoint curvePointToUpdate = new CurvePoint();
		curvePointToUpdate.setTerm(100.0);
		curvePointToUpdate.setValue(100.0);
		
		mockMvc.perform(post("/curvePoint/update/1")
				.param("term", curvePointToUpdate.getTerm().toString())
				.param("value", curvePointToUpdate.getValue().toString()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("curvePoint/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /curvePoint/delete/{id}")
	void getCurvePointToDeleteTest() throws Exception {
		mockMvc.perform(get("/curvePoint/delete/1"))
			.andExpect(view().name("redirect:/curvePoint/list"))
			.andExpect(status().isFound());
	}

}
