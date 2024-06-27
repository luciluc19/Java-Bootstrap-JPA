package com.springBoot.EwdOlymp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(classes = { OlympicApp.class, NieuweWedstrijdController.class })
@AutoConfigureMockMvc
public class NieuweWedstrijdControllerTest {

    @Autowired
    private MockMvc mockMvc;

  



    @Test
    @WithMockUser(username = "admin1", password = "123", roles = { "ADMIN" })
    public void testGetNieuweWedstrijd() throws Exception {
        mockMvc.perform(get("/nieuweWedstrijd").param("sport", String.valueOf("Voetbal")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("wedstrijd"))
                .andExpect(model().attributeExists("stadiums"))
                .andExpect(model().attributeExists("nieuweWedstrijd"))
                .andExpect(view().name("nieuweWedstrijdForm"));
    }

    @Test
    @WithMockUser(username = "gebruiker1", password = "123", roles = {})
    public void testAccessDenied() throws Exception {
        mockMvc.perform(get("/nieuweWedstrijd").param("sport", "Armworstelen"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin1", password = "123", roles = { "ADMIN" })
    public void testVerwerkNieuweWedstrijdZonderError() throws Exception {
        NieuweWedstrijdValidator nieuweWedstrijd = new NieuweWedstrijdValidator();

        nieuweWedstrijd.setAantalPlaatsen(10);
        nieuweWedstrijd.setDatum(LocalDate.of(2024, 7, 31));
        nieuweWedstrijd.setAanvangsuur(LocalTime.of(12, 0));
        nieuweWedstrijd.setOlympischNummer1("55554");
        nieuweWedstrijd.setOlympischNummer2("55555");
        nieuweWedstrijd.setSportNaam("Voetbal");
        nieuweWedstrijd.setAantalPlaatsen(10);
        nieuweWedstrijd.setTicketPrijs(10.0);
        nieuweWedstrijd.setDiscipline1("TestDiscipline1");
        nieuweWedstrijd.setDiscipline2("TestDiscipline2");
        nieuweWedstrijd.setIdStadium(1);

        mockMvc.perform(post("/nieuweWedstrijd")
                .flashAttr("nieuweWedstrijd", nieuweWedstrijd)
                .param("sport", "Voetbal")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wedstrijd?sport=Voetbal"));
    }


    @Test
    @WithMockUser(username = "admin1", password = "123", roles = { "ADMIN" })
    public void testVerwerkNieuweWedstrijdMetError() throws Exception {
        NieuweWedstrijdValidator nieuweWedstrijd = new NieuweWedstrijdValidator();

        nieuweWedstrijd.setAantalPlaatsen(10);
        nieuweWedstrijd.setDatum(LocalDate.of(2024, 7, 31));
        nieuweWedstrijd.setAanvangsuur(LocalTime.of(12, 0));
        nieuweWedstrijd.setOlympischNummer1("55554");
        nieuweWedstrijd.setOlympischNummer2("55554");
        nieuweWedstrijd.setSportNaam("Voetbal");
        nieuweWedstrijd.setAantalPlaatsen(10);
        nieuweWedstrijd.setTicketPrijs(10.0);
        nieuweWedstrijd.setDiscipline1("TestDiscipline1");
        nieuweWedstrijd.setDiscipline2("TestDiscipline2");
        nieuweWedstrijd.setIdStadium(1);

        mockMvc.perform(post("/nieuweWedstrijd")
                .flashAttr("nieuweWedstrijd", nieuweWedstrijd)
                .param("sport", "Voetbal")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("nieuweWedstrijdForm"));
            }

    @Test
    @WithMockUser(username = "admin1", password = "123", roles = { "USER" })
    public void testPostNieuweWedstrijdFouteRole() throws Exception {
        mockMvc.perform(post("/nieuweWedstrijd")
                .param("Sport", String.valueOf("voetbal")))
                .andExpect(status().isForbidden());
    }
}
