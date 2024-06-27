package com.springBoot.EwdOlymp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


import repository.TicketRepository;
import repository.UserRepository;
import service.TicketService;
import service.WedstrijdService;

import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;



@SpringBootTest(classes = {OlympicApp.class , NieuweTicketControllerTest.class})
@AutoConfigureMockMvc
public class NieuweTicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @MockBean
    private WedstrijdService wedstrijdService;


    @MockBean
    private UserRepository userRepo;

    @MockBean
    private TicketRepository ticketRepo;

    @Mock
    private Principal principal;


    @Test
    @WithMockUser(username = "gebruiker1", password = "123", roles = { "USER" })
    public void testGetTicketPage() throws Exception {
        mockMvc.perform(get("/nieuweTicket").param("idWedstrijd", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("wedstrijd"))
                .andExpect(model().attributeExists("maxPlaatsen"))
                .andExpect(model().attributeExists("idWedstrijd"))
                .andExpect(model().attributeExists("gekochteTicketten"))
                .andExpect(view().name("TickettenKopen"));
    }

    @Test
    @WithMockUser(username = "gebruiker1", password = "123", roles = { "Admin" })
    public void testAccessDeniedAdmin() throws Exception {
        mockMvc.perform(get("/nieuweTicket").param("idWedstrijd", "1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "gebruiker1", password = "123", roles = {})
    public void testAccessDeniedGeenRol() throws Exception {
        mockMvc.perform(get("/nieuweTicket").param("idWedstrijd", "1"))
                .andExpect(status().isForbidden());
    }

    

  

    @Test
    @WithMockUser(username = "gebruiker1", password = "123", roles = { "USER", })
    public void testNieuweTicket() throws Exception {
        int idWedstrijd = 1;
        int aantalTickets = 2;

        mockMvc.perform(post("/nieuweTicket")
                .with(csrf())
                .param("idWedstrijd", String.valueOf(idWedstrijd))
                .param("aantalTickets", String.valueOf(aantalTickets)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wedstrijd?sport=Voetbal"));
            }

    @Test
    @WithMockUser(username = "gebruiker1", password = "123", roles = {  "ADMIN" })
    public void testNieuweTicketFouteRole() throws Exception {
        int idWedstrijd = 1;
        int aantalTickets = 2;

        mockMvc.perform(post("/nieuweTicket")
                .param("idWedstrijd", String.valueOf(idWedstrijd))
                .param("aantalTickets", String.valueOf(aantalTickets)))
                .andExpect(status().isForbidden());
            }
}