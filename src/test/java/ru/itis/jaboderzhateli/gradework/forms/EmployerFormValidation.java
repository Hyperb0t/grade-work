package ru.itis.jaboderzhateli.gradework.forms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpEmployerForm;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpTeacherForm;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployerFormValidation {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenNull403() throws Exception {
        var form = new SignUpEmployerForm();

        mockMvc.perform(post("/signUp")
                .content(objectMapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().is4xxClientError());
    }

//    @Test
//    void whenFull200() throws Exception {
//        var form = new SignUpEmployerForm("login", "12345678", "12345678",
//                "organisation", "psrn", "name", "sur",
//                "middle", "phone", "sample@exit.com", Collections.singletonList("link"), true);
//
//        mockMvc.perform(post("/signUp/teacher")
//                .content(objectMapper.writeValueAsString(form))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .with(csrf()))
//                .andExpect(status().isOk());
//    }

    @Test
    void whenNoCsrf403() throws Exception {
        var form = new SignUpEmployerForm();

        mockMvc.perform(post("/signUp")
                .content(objectMapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}
