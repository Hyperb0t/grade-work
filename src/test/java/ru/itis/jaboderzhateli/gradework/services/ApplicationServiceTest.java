package ru.itis.jaboderzhateli.gradework.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.jaboderzhateli.gradework.models.*;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.JobApplicationRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ApplicationService;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ApplicationServiceTest {

    @Autowired
    private JobApplicationRepository applicationRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private ApplicationService applicationService;

    private static Student student;
    private static Employer employer;

    @BeforeAll
    public static void setup() {
        student = Student.builder()
                .login("admin")
                .password(passwordEncoder.encode("123"))
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
                .build();
        employer = Employer.builder().build();

    }

    @Test
    public void jobIsEqualForEmployer() {
        var jobApp = JobApplication.builder()
                .employer(employer)
                .student(student)
                .read(false)
                .sentAt(Instant.now())
                .build();
        var tempStudent = studentRepository.save(student);
        var tempEmployer = employerRepository.save(employer);
        applicationService.apply(student, employer);
        Assertions.assertEquals(applicationRepository.findAllByEmployerAndReadIs(jobApp.getEmployer(),
                jobApp.getRead()).get(0).getEmployer().getCompanyName(), applicationRepository.findAllByEmployerAndReadIs(employer, false).get(0).getEmployer().getCompanyName());
    }

}
