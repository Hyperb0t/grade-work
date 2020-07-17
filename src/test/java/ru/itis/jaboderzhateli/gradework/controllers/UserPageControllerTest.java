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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
public class UserPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "teacher", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void whenAuth30x() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void whenNotAuth() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void whenRequestAdmin() throws Exception {
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk());
    }

    @Test
    void whenRequestStudent() throws Exception {
        mockMvc.perform(get("/user/2"))
                .andExpect(status().isOk());
    }

    @Test
    void whenRequestEmpl() throws Exception {
        mockMvc.perform(get("/user/4"))
                .andExpect(status().isOk());
    }

}
