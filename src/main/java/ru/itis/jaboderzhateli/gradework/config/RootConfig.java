package ru.itis.jaboderzhateli.gradework.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import ru.itis.jaboderzhateli.gradework.models.*;
import ru.itis.jaboderzhateli.gradework.models.Role;
import ru.itis.jaboderzhateli.gradework.repositories.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@PropertySource("classpath:application.properties")
@AllArgsConstructor
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "ru.itis.jaboderzhateli.gradework")
public class RootConfig {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final EmployerRepository employerRepository;
    private final FacultyRepository facultyRepository;
    private final InstituteRepository instituteRepository;
    private final CompetenceRepository competenceRepository;
    private final StudentCompetenceRepository studentCompetenceRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void loadEntities() {
        instituteRepository.save(Institute.builder().name("Институт экологии и природопользования").build());
        instituteRepository.save(Institute.builder().name("Институт геологии и нефтегазовых технологий").build());
        instituteRepository.save(Institute.builder().name("Институт математики и механики им. Н.И. Лобачевского").build());
        instituteRepository.save(Institute.builder().name("Институт физики").build());
        instituteRepository.save(Institute.builder().name("Химический институт им. А.М. Бутлерова").build());
        instituteRepository.save(Institute.builder().name("Юридический факультет").build());
        instituteRepository.save(Institute.builder().name("Институт вычислительной математики и информационных технологий").build());
        instituteRepository.save(Institute.builder().name("Институт филологии и межкультурной коммуникации").build());
        instituteRepository.save(Institute.builder().name("Высшая школа информационных технологий и интеллектуальных систем").build());
        instituteRepository.save(Institute.builder().name("Институт фундаментальной медицины и биологии").build());
        instituteRepository.save(Institute.builder().name("Инженерный институт").build());
        instituteRepository.save(Institute.builder().name("Институт международных отношений").build());
        instituteRepository.save(Institute.builder().name("Высшая школа бизнеса").build());
        instituteRepository.save(Institute.builder().name("Институт социально-философских наук и массовых коммуникаций").build());
        instituteRepository.save(Institute.builder().name("Институт управления, экономики и финансов").build());
        instituteRepository.save(Institute.builder().name("Высшая школа государственного и муниципального управления").build());
        instituteRepository.save(Institute.builder().name("Институт передовых образовательных технологий").build());
        instituteRepository.save(Institute.builder().name("Набережночелнинский институт КФУ").build());
        instituteRepository.save(Institute.builder().name("Елабужский институт КФУ").build());

        userRepository.save(User.builder().login("admin").password(passwordEncoder.encode("123")).role(Role.ADMINISTRATION).build());

        Student student = Student.builder()
                .login("student")
                .password(passwordEncoder.encode("123"))
                .role(Role.STUDENT)
                .name("Михаил")
                .surname("Студентов")
                .middleName("Александрович")
                .birthday(new Date(944611200000L))
                .group("11-804")
                .yearStart((short) 2018)
                .yearGraduate((short) 2020)
                .course((byte) 3)
                .average((byte) 93)
                .phone("89503135579")
                .email("rodsher111@gmail.com")
                .bio("Гигант мысли")
                .link("https://vk.com").build();
        Faculty faculty = Faculty.builder().name("Программная инженерия").build();
        facultyRepository.save(faculty);
        student.setFaculty(faculty);
        student.setInstitute(Institute.builder().id(9L).build());
        Competence competence1 = Competence.builder().name("Java").build();
        Competence competence2 = Competence.builder().name("Data mining").build();
        competenceRepository.save(competence1);
        competenceRepository.save(competence2);
        studentRepository.save(student);
        studentCompetenceRepository.save(new StudentCompetence(student, competence1, true));
        studentCompetenceRepository.save(new StudentCompetence(student, competence2, false));


        Teacher teacher = Teacher.builder()
                .login("teacher")
                .password(passwordEncoder.encode("123"))
                .name("Михаил")
                .role(Role.TEACHER)
                .surname("Преподавателев")
                .middleName("Александрович")
                .institute(Institute.builder().id(9L).build())
                .experience((byte) 30)
                .position("Основной работник, гений")
                .phone("89503135579")
                .email("rodsher111@gmail.com")
                .link("https://vk.com").build();
        List<Competence> competences1 = new ArrayList<>();
        competences1.add(competence1);
        competences1.add(competence2);
        teacher.setCompetence(competences1);
        teacherRepository.save(teacher);

        Employer employer = Employer.builder()
                .login("employer")
                .password(passwordEncoder.encode("123"))
                .role(Role.EMPLOYER)
                .name("Михаил")
                .surname("Работодателев")
                .middleName("Александрович")
                .phone("89503135579")
                .email("rodsher111@gmail.com")
                .companyName("ООО 'ГЕНИЕПРОИЗВОДСТВО'")
                .psrn("1231241352361").build();
        employerRepository.save(employer);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multiPartResolver() {
        var resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(52428800);
        return resolver;
    }

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public MappingJackson2HttpMessageConverter customJson() {
        return new MappingJackson2HttpMessageConverter(
                new Jackson2ObjectMapperBuilder()
                        .indentOutput(true)
                        .serializationInclusion(JsonInclude.Include.NON_NULL)
                        .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                        .build()
        );
    }

}
