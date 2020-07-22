package ru.itis.jaboderzhateli.gradework.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.jaboderzhateli.gradework.config.RootConfig;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
public class ResumeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getFormNotAuth_is403() throws Exception {
        mockMvc.perform(post("/resume/create"))
                .andExpect(status()
                        .isForbidden());
    }

    @Test
    @WithUserDetails(value = "student", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void getFormAuth_isOk() throws Exception {
        mockMvc.perform(get("/resume/edit"))
                .andExpect(status()
                        .isOk());
    }

    @Test
    @WithUserDetails(value = "student", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void postFormAuth_is3xx() throws Exception {
        mockMvc.perform(post("/resume/edit")
                .with(csrf()))
                .andExpect(status()
                        .is3xxRedirection());
    }

//    @Test
//    public void postFormNotAuth_is403() throws Exception {
//        mockMvc.perform(post("/create")
//                .with(csrf()))
//                .andExpect(status()
//                        .isForbidden());
//    }

    @Test
    public void postFormNotCsrf_is403() throws Exception {
        mockMvc.perform(post("/resume/create"))
                .andExpect(status()
                        .isForbidden());
    }

}
