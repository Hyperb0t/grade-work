package ru.itis.jaboderzhateli.gradework.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.jaboderzhateli.gradework.config.TestApplicationConfig;
import ru.itis.jaboderzhateli.gradework.models.Student;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class StudentCompetenceRepositoryTest {

    private StudentCompetenceRepository studentCompetenceRepository;
    private StudentRepository studentRepository;
    private CompetenceRepository competenceRepository;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        studentCompetenceRepository = context.getBean(StudentCompetenceRepository.class);
        studentRepository = context.getBean(StudentRepository.class);
        competenceRepository = context.getBean(CompetenceRepository.class);
    }

    @Test
    void studentSaveIdGeneration() {
        Student st1 = Student.builder().name("Lol").build();
        Student st2 = Student.builder().name("Kek").build();
        studentRepository.save(st1);
        studentRepository.save(st2);
        StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .forEach(student -> log.info(student.toString()));
    }
}
