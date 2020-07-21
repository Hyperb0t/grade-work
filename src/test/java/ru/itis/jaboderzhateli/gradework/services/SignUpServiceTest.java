package ru.itis.jaboderzhateli.gradework.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpStudentForm;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpTeacherForm;
import ru.itis.jaboderzhateli.gradework.models.Faculty;
import ru.itis.jaboderzhateli.gradework.models.Institute;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.Teacher;
import ru.itis.jaboderzhateli.gradework.services.interfaces.SignUpService;

import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SignUpServiceTest {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void signUpStudentFull_shouldEqual(){
        var form = new SignUpStudentForm("login","password", "password", "name",
                "sur","mid","Программная инженерия", "Высшая школа информационных технологий и интеллектуальных систем", "11-804",
                "link","phone",Short.valueOf("2000"), Short.valueOf("2004"), Byte.valueOf("90"), new Date(2));
        var student = signUpService.signUp(form);
        assertThat(student.equals(Student.builder()
                .login(form.getLogin())
                .password(passwordEncoder.encode(form.getPassword()))
                .yearGraduate(Short.valueOf("2004"))
                .yearStart(Short.valueOf("2000"))
                .name("name")
                .surname("sur")
                .middleName("mid")
                .group("11-804")
                .email("login")
                .phone("phone")
                .institute(Institute.builder().id(1L).name("Высшая школа информационных технологий и интеллектуальных систем").build())
                .link("link")
                .faculty(Faculty.builder().id(1L).name("Программная инженерия").build())
                .birthday(new Date(2))
                .average(Byte.valueOf("90"))
                .build()));
    }

    @Test
    public void signUpEmployerFull_shouldEqual(){
        var form = new SignUpTeacherForm("login","password","password", "name", "surname","mid", Byte.valueOf("2"),
                "Высшая школа информационных технологий и интеллектуальных систем", "position", "link", Collections.emptyList());
        var teacher = signUpService.signUp(form);
        assertThat(teacher.equals(Teacher.builder()
                .competence(Collections.emptyList())
                .institute(Institute.builder().id(1L).name("Высшая школа информационных технологий и интеллектуальных систем").build())
                .link(form.getLink())
                .login(form.getLogin())
                .middleName(form.getMiddleName())
                .name(form.getName())
                .password(passwordEncoder.encode(form.getPassword()))
                .position(form.getPosition())
                .surname(form.getSurname())
                .experience(form.getExperience())
                .build()));

    }

}
