package com.springBoot.EwdOlymp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = OlympicApp.class)
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
public class loginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginMetFouteUser() throws Exception {
        mockMvc.perform(formLogin().user("invalid").password("invalid"))
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void testLoginMetJuisteUser() throws Exception {
        mockMvc.perform(formLogin().user("gebruiker1").password("12345678"));
    }

}
