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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
public class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPage_isOk() throws Exception {
        mockMvc.perform(get("/chat"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void getChatPageNotAuth_isRedirect() throws Exception {
        mockMvc.perform(get("/chat?ch=1"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void getChatPageAuth_isOk() throws Exception {
        mockMvc.perform(get("/user/2/createChat"))
                .andExpect(status().is3xxRedirection());
    }

}
