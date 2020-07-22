package ru.itis.jaboderzhateli.gradework.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.jaboderzhateli.gradework.config.RootConfig;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
public class ConfirmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "teacher", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void whenAuth200() throws Exception {
        mockMvc.perform(get("/confirm/competences"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "teacher", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void whenNotAuthPost_isForbidden() throws Exception {
        mockMvc.perform(post("/confirm/competences"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = "teacher", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void whenAuthPost_isOk() throws Exception {
        mockMvc.perform(post("/confirm/competences")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }
}
