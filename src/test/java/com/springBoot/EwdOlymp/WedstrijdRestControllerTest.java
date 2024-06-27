package com.springBoot.EwdOlymp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import repository.UserRepository;
import java.util.Collections;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import domain.User;

@SpringBootTest
@AutoConfigureMockMvc
public class WedstrijdRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserRepository userRepository;

	@BeforeEach
	public void before() {
		User gebruiker = new User(1, "gebruiker1", "123", true, Collections.singletonList("ROLE_USER"));
		User admin = new User(2, "gebruiker2", "123", true, Collections.singletonList("ROLE_ADMIN"));

		userRepository.save(gebruiker);
		userRepository.save(admin);
	}

	@Test
	@WithMockUser(username = "gebruiker1", password = "123", roles = { "USER", "ADMIN" })
	public void testGetMatchesBySport() throws Exception {
		mockMvc.perform(get("/rest/wedstrijdenBySport/Voetbal"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].idWedstrijd").value(1))
				.andExpect(jsonPath("$[0].datum").value("2024-07-08"));
	}

	@Test
	@WithMockUser(username = "gebruiker1", password = "123", roles = { "USER", "ADMIN" })
	public void testGetAantalOpenPlaatsenByWedstrijd() throws Exception {
		mockMvc.perform(get("/rest/AantalPlaatsenByWedstrijdId/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(21));
	}

	@Test
	@WithMockUser(username = "gebruiker1", password = "123", roles = { "USER", "ADMIN" })
	public void testGetAantalOpenPlaatsenByWedstrijdFoutteUrl() throws Exception {
		mockMvc.perform(get("/rest/AantalPlaatsenByWedstrijdId/dddddd"))
				.andExpect(status().isBadRequest());
	}

	@Test
	@WithMockUser(username = "gebruiker1", password = "123", roles = { "USER", "ADMIN" })
	public void testGetMatchesBySportFoutteUrl() throws Exception {
		mockMvc.perform(get("/rest/wedstrijdenBySport/Voetbalddddddddd"))
				.andExpect(status().isNotFound());
	}

}
