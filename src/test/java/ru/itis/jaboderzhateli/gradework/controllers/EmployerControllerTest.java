package ru.itis.jaboderzhateli.gradework.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
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
public class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "employer", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void getEmployersPage_isOk() throws Exception {
        mockMvc.perform(get("/employers"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "employer", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void getApplicationsAuth_isOk() throws Exception {
        mockMvc.perform(get("/applications"))
                .andExpect(status().isOk());
    }

    @Test
    public void getApplicationsNotAuth_isRedirect() throws Exception {
        mockMvc.perform(get("/applications"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails(value = "student", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void getApplyAuth_is3xx() throws Exception {
        mockMvc.perform(get("/apply/1"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails(value = "employer", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void getApplyWrongAuth_is403() throws Exception {
        mockMvc.perform(get("/apply/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getApplyNotAuth_isRedirect() throws Exception {
        mockMvc.perform(get("/apply/1"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void postApplicationWithCsrf_is3xx() throws Exception {
        mockMvc.perform(post("/applications")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void postApplicationNoCsrf_is403() throws Exception {
        mockMvc.perform(post("/applications"))
                .andExpect(status().isForbidden());
    }



}
