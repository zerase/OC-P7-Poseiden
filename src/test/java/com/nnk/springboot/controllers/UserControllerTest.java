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

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@SpringBootTest
@WithMockUser(username="admin", password="test", roles="ADMIN")
@AutoConfigureMockMvc(addFilters=false)
@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	UserRepository userRepository;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		User user = new User();
		user.setUsername("username");
		user.setPassword("Admin123+");
		user.setFullname("fullname");
		user.setRole("USER");
		userRepository.save(user);
	}

	@AfterEach
	void tearDown() throws Exception {
	}


	@Test
	@DisplayName("Get request /user/list")
	void getUserTest() throws Exception {
		mockMvc.perform(get("/user/list"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("user/list"))
			.andExpect(content().string(containsString("username")));
	}
	
	@Test
	@DisplayName("Get request /user/add")
	void getUserAddTest() throws Exception {
		mockMvc.perform(get("/user/add"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("user/add"))
			.andExpect(model().attributeExists("user"));
	}

	@Test
	@DisplayName("Post request /user/validate")
	void postNewUserTest() throws Exception {
		User userToAdd = new User();
		userToAdd.setUsername("username");
		userToAdd.setPassword("Admin123+");
		userToAdd.setFullname("fullname");
		userToAdd.setRole("USER");
		
		mockMvc.perform(post("/user/validate")
				.param("username", userToAdd.getUsername())
				.param("password", userToAdd.getPassword())
				.param("fullname", userToAdd.getFullname())
				.param("role", userToAdd.getRole()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/user/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /user/validate with errors")
	void postNewUserWithErrorsTest() throws Exception {
		User userToAdd = new User();
		userToAdd.setUsername("");
		userToAdd.setPassword("Admin123+");
		userToAdd.setFullname("fullname");
		userToAdd.setRole("USER");
		
		mockMvc.perform(post("/user/validate")
				.param("username", userToAdd.getUsername())
				.param("password", userToAdd.getPassword())
				.param("fullname", userToAdd.getFullname())
				.param("role", userToAdd.getRole()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("user/add"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /user/update/{id}")
	void getUserUpdateTest() throws Exception {
		mockMvc.perform(get("/user/update/1"))
			.andExpect(model().attributeExists("user"))
			.andExpect(view().name("user/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Post request /user/update/{id}")
	void postUserToUpdateTest() throws Exception {
		User userToUpdate = new User();
		userToUpdate.setUsername("username");
		userToUpdate.setPassword("Admin123+");
		userToUpdate.setFullname("fullname");
		userToUpdate.setRole("USER");
		
		mockMvc.perform(post("/user/update/1")
				.param("username", userToUpdate.getUsername())
				.param("password", userToUpdate.getPassword())
				.param("fullname", userToUpdate.getFullname())
				.param("role", userToUpdate.getRole()))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/user/list"))
			.andExpect(status().isFound());
	}
	
	@Test
	@DisplayName("Post request /user/update/{id} with errors")
	void postUserToUpdateWithErrorsTest() throws Exception {
		User userToUpdate = new User();
		userToUpdate.setUsername("username");
		userToUpdate.setPassword("");
		userToUpdate.setFullname("fullname");
		userToUpdate.setRole("USER");
		
		mockMvc.perform(post("/user/update/1")
				.param("username", userToUpdate.getUsername())
				.param("password", userToUpdate.getPassword())
				.param("fullname", userToUpdate.getFullname())
				.param("role", userToUpdate.getRole()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("user/update"))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get request /user/delete/{id}")
	void getUserToDeleteTest() throws Exception {
		mockMvc.perform(get("/user/delete/1"))
			.andExpect(view().name("redirect:/user/list"))
			.andExpect(status().isFound());
	}

}
