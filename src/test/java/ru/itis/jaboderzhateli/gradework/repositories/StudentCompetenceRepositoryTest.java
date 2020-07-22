package ru.itis.jaboderzhateli.gradework.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itis.jaboderzhateli.gradework.models.Student;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.stream.StreamSupport;

/**
 * @author Vitaly
 */


@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
public class StudentCompetenceRepositoryTest {

    @Autowired
    private StudentCompetenceRepository studentCompetenceRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CompetenceRepository competenceRepository;

//    @BeforeEach
//    void setUp() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
//        studentCompetenceRepository = context.getBean(StudentCompetenceRepository.class);
//        studentRepository = context.getBean(StudentRepository.class);
//        competenceRepository = context.getBean(CompetenceRepository.class);
//    }

    @Test
    void dbUrl(@Autowired DataSource dataSource) {
        String url = "unknown";
        try {
            url = dataSource.getConnection().getMetaData().getURL();
        }catch (Exception ignored) {}
        log.info("USING DATASOURCE: " + url);
    }

    @Test
    void studentSaveIdGeneration() {
        Student st1 = Student.builder().name("Lol").build();
        Student st2 = Student.builder().name("Kek").build();
        studentRepository.save(st1);
        studentRepository.save(st2);
        StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .forEach(student -> log.info(student.getId() + " " + student.getName()));
    }
}
