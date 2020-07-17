package ru.itis.jaboderzhateli.gradework.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
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
public class SignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMINISTRATION")
    public void getSignUpTeacherForm200() throws Exception {
        mockMvc.perform( get("/signUp/teacher"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMINISTRATION")
    public void getSignUpStudentForm200() throws Exception {
        mockMvc.perform(get("/signUp/student"))
                .andExpect(status().isOk());
    }

    @Test
    public void getSignUpEmplForm200() throws Exception {
        mockMvc.perform(get("/signUp"))
                .andExpect(status().isOk());
    }


}
