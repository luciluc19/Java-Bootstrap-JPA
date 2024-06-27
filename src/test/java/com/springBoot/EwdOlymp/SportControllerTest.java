package com.springBoot.EwdOlymp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class SportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "gebruiker1", password = "123", roles = { "USER" })
    public void testGetSport() throws Exception {
        mockMvc.perform(get("/sport"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("sports"))
                .andExpect(model().attributeExists("hasTickets"))
                .andExpect(view().name("sporten"));
    }

    @Test
    @WithMockUser(username = "gebruiker1", password = "123", roles = {})
    public void testAccessDenied() throws Exception {
        mockMvc.perform(get("/sport"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "gebruiker1", password = "123", roles = { "USER" })
    @WithUserDetails("gebruiker1")
    public void testGetTicketten() throws Exception {
        mockMvc.perform(get("/sport/mijnTicketten?"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("tickettten"))
                .andExpect(model().attributeExists("aantalTicketsInTotaal"))
                .andExpect(view().name("mijnTicketten"));
    }

}