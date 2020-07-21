package ru.itis.jaboderzhateli.gradework.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.itis.jaboderzhateli.gradework.config.RootConfig;

import java.io.FileInputStream;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void getSignUpTeacherForm_isOk() throws Exception {
        mockMvc.perform(get("/signUp/teacher"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMINISTRATION")
    public void getSignUpStudentForm_isOk() throws Exception {
        mockMvc.perform(get("/signUp/student"))
                .andExpect(status().isOk());
    }

    @Test
    public void getSignUpEmplForm_isOk() throws Exception {
        mockMvc.perform(get("/signUp"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMINISTRATION")
    public void getSignUpStudentFormFile_isOk() throws Exception {
        mockMvc.perform(get("/signUp/student/file"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMINISTRATION")
    public void getSignUpTeacherFormFile_isOk() throws Exception {
        mockMvc.perform(get("/signUp/teacher/file"))
                .andExpect(status().isOk());
    }

    @Test
    public void postSignUpEmployer_is3xx() throws Exception {
        mockMvc.perform(post("/signUp")
                .param("password", "123")
                .with(csrf()))
                .andExpect(status()
                        .is3xxRedirection());
    }

    @Test
    public void postSignUpEmployerNoCsrf_is403() throws Exception {
        mockMvc.perform(post("/signUp"))
                .andExpect(status()
                        .isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void postSignUpStudent_is3xx() throws Exception {
        mockMvc.perform(post("/signUp/student")
                .param("password", "123")
                .param("faculty", "Программная инженерия")
                .param("institute", "Высшая школа информационных технологий и интеллектуальных систем")
                .with(csrf()))
                .andExpect(status()
                        .is3xxRedirection());
    }

    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void postSignUpStudentNoCsrf_is403() throws Exception {
        mockMvc.perform(post("/signUp/student")
                .param("password", "123")
                .param("faculty", "Программная инженерия")
                .param("institute", "Высшая школа информационных технологий и интеллектуальных систем"))
                .andExpect(status()
                        .isForbidden());
    }

    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void postSignUpTeacher_is3xx() throws Exception {
        mockMvc.perform(post("/signUp/teacher")
                .param("password", "123")
                .param("institute", "Высшая школа информационных технологий и интеллектуальных систем")
                .with(csrf()))
                .andExpect(status()
                        .is3xxRedirection());
    }

    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void postSignUpTeacherNoCsrf_is403() throws Exception {
        mockMvc.perform(post("/signUp/teacher")
                .param("password", "123")
                .param("faculty", "Программная инженерия")
                .param("institute", "Высшая школа информационных технологий и интеллектуальных систем"))
                .andExpect(status()
                        .isForbidden());
    }

    @Test
    public void postSignUpTeacherFileNotAuth_is3xx() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "teacher.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "teacher.xlsx".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/signUp/teacher/file")
                .file(file)
                .with(csrf()))
                .andExpect(status()
                        .is3xxRedirection());
    }

    @Test
    public void postSignUpStudentFileNotAuth_is3xx() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "student.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "student.xlsx".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/signUp/student/file")
                .file(file)
                .with(csrf()))
                .andExpect(status()
                        .is3xxRedirection());
    }

    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void postSignUpTeacherFile_isOk() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "teacher.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new FileInputStream("src/test/resources/teacher.xlsx"));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/signUp/teacher/file")
                .file(file)
                .with(csrf()))
                .andExpect(status()
                        .isOk());
    }

    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsServiceImpl")
    public void postSignUpStudentFile_isOk() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "student.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new FileInputStream("src/test/resources/student.xlsx"));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/signUp/student/file")
                .file(file)
                .with(csrf()))
                .andExpect(status()
                        .isOk());
    }


}
