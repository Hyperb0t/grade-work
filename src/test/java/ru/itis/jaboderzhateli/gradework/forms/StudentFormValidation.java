package ru.itis.jaboderzhateli.gradework.forms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpStudentForm;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpTeacherForm;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentFormValidation {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenNull403() throws Exception {
        var form = new SignUpStudentForm();

        mockMvc.perform(post("/signUp/student")
                .content(objectMapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

//    @Test
//    void whenFull200() throws Exception {
//        var form = new SignUpTeacherForm("login", "12345678", "12345678", "namething",
//                "surname", "mid", Byte.valueOf("12"), "ВШ ИТИС", "position",
//                "link", Collections.singletonList("Java"));
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
        var form = new SignUpStudentForm();

        mockMvc.perform(post("/signUp/student")
                .content(objectMapper.writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
