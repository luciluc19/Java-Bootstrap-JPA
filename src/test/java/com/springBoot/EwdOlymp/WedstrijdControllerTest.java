package com.springBoot.EwdOlymp;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;




@SpringBootTest
@AutoConfigureMockMvc
class WedstrijdControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "gebruiker1",  password = "123" , roles = {"USER", "ADMIN"})
	public void testGetRequest() throws Exception {
		mockMvc.perform(get("/wedstrijd").param("sport", "Voetbal"))
			.andExpect(status().isOk())
			.andExpect(view().name("wedstrijd"));
	}

	@Test
	@WithMockUser(username = "gebruiker1",  password = "123" , roles = {})
	public void testAccessDenied() throws Exception {
		mockMvc.perform(get("/wedstrijd").param("sport", "Voetbal"))
		.andExpect(status().isForbidden());
	}





}
