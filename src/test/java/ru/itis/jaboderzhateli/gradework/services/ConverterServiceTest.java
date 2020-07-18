package ru.itis.jaboderzhateli.gradework.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.StudentPoijiDto;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.TeacherPoijiDto;
import ru.itis.jaboderzhateli.gradework.models.*;
import ru.itis.jaboderzhateli.gradework.repositories.FacultyRepository;
import ru.itis.jaboderzhateli.gradework.repositories.InstituteRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ConverterService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConverterServiceTest {

    @Autowired
    private ConverterService converterService;


    @Before
    public void setup() {
    }

    @Test(expected = NullPointerException.class)
    public void nullWhen_NotValid() {
        var dto = new StudentPoijiDto();
        var student = converterService.convert(dto);
        assertThat(student).isEqualTo(new Student());
    }

    @Test
    public void equalStudentWhenValid() {
        var dto = new StudentPoijiDto(2000, "login", 2010, 2, "me", "name",
                "surname", "mid", "11-804", "email", "phone",
                "institute", "link", "faculty", new Date(2), 12, "password");
        var student = converterService.convert(dto);
        assertThat(student).isEqualTo(Student.builder()
                .yearGraduate(Short.valueOf("2010"))
                .yearStart(Short.valueOf("2000"))
                .course(Byte.valueOf("2"))
                .bio("me")
                .name("name")
                .surname("surname")
                .middleName("mid")
                .group("11-804")
                .email("email")
                .phone("phone")
                .institute(Institute.builder().name("institute").build())
                .link("link")
                .faculty(Faculty.builder().name("faculty").build())
                .birthday(new Date(2))
                .average(Byte.valueOf("12"))
                .password("password")
                .build());
    }

    @Test
    public void equalTeacherWhenValid() {
        var dto = new TeacherPoijiDto("name", "surname", "mid", "email", "phone",
                "login", "password", "position", "link",
                2, "Инженерный институт");
        var teacher = converterService.convert(dto);
        assertThat(teacher.equals(Teacher.builder()
                .name("name")
                .surname("surname")
                .middleName("mid")
                .email("email")
                .phone("phone")
                .login("login")
                .password("password")
                .position("position")
                .link("link")
                .experience(Byte.valueOf("2"))
                .institute(Institute.builder().id(11L).name("Инженерный институт").build())
                .build()));
    }


}
