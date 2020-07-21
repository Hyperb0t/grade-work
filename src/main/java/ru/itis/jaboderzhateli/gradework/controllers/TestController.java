package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.jaboderzhateli.gradework.models.*;
import ru.itis.jaboderzhateli.gradework.repositories.*;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;

import java.util.*;

@Controller
@PreAuthorize("hasRole('ADMINISTRATION')")
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final InstituteRepository instituteRepository;
    private final FacultyRepository facultyRepository;
    private final CompetenceRepository competenceRepository;

    @GetMapping("/me")
    @ResponseBody
    public String userInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails.getUser().toString();
    }

    @GetMapping("/signIn")
    public String getSignInForm() {
        return "auth/sign_in";
    }

    @PostMapping("/signIn")
    public String signIn(@RequestParam Map<String, String> params) {
        System.out.println("PARAMS: " + params);
        return "redirect:/";
    }


    @GetMapping("/signUp/student")
    public String getStudentSignUpForm(ModelMap map) {
//        Student student = Student.builder()
//                .name("bob")
//                .surname("bib")
//                .middleName("bub")
//                .group("11-804")
//                .yearStart((short) 2018)
//                .yearGraduate((short) 2020).build();
//        student.setLogin("gg");
//        student.setPassword("bb");
//        student.setRole(Role.STUDENT);
//        studentRepository.save(student);
//        User user = new User(null, "gg", "bb", Role.STUDENT);
//        userRepository.save(user);
//        Student student = Student.builder().login("bb").password("gg").name("bob").build();
//        studentRepository.save(student);
//        User user = User.builder().role(Role.STUDENT).login("tt").password("rr").build();
//        userRepository.save(user);
//        studentRepository.save(Student.builder().id(2L).name("TOM").build());
        List<String> institutes = new ArrayList<>();
        institutes.add("ИТИС");
        institutes.add("ИВМИИТ");
        institutes.add("Юрфак");
        institutes.add("Эконом");
        List<String> faculties = new ArrayList<>();
        faculties.add("Программная инженерия");
        faculties.add("Физика");
        faculties.add("Биология");
        faculties.add("Математика");
        map.put("institutes", institutes);
        map.put("faculties", faculties);
        return "auth/sign_up_student";
    }

    @PostMapping("/signUp/student")
    public String signUpStudent(@RequestParam Map<String, String> params) {
        System.out.println("PARAMS: " + params);
        return "redirect:/";
    }

    @GetMapping("/signUp/teacher")
    public String getTeacherSignUpForm(ModelMap map) {
        List<String> institutes = new ArrayList<>();
        institutes.add("ИТИС");
        institutes.add("ИВМИИТ");
        institutes.add("Юрфак");
        institutes.add("Эконом");
        List<String> competences = new ArrayList<>();
        competences.add("Программная инженерия");
        competences.add("Физика");
        competences.add("Биология");
        competences.add("Математика");
        map.put("institutes", institutes);
        map.put("competences", competences);
        return "auth/sign_up_teacher";
    }

    @PostMapping("/signUp/teacher")
    public String signUpTeacher(@RequestParam Map<String, String> params) {
        System.out.println("PARAMS: " + params);
        return "redirect:/";
    }

    @GetMapping("/signUp")
    public String getEmployerSignUpForm() {
        return "auth/sign_up_employer";
    }

    @PostMapping("/signUp")
    public String signUpEmployer(@RequestParam Map<String, String> params) {
        System.out.println("PARAMS: " + params);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getLanding() {
        return "main/landing";
    }

    @GetMapping("/student")
    public String getStudentPage(ModelMap map) {
        Student student = Student.builder()
                .name("Михаил")
                .surname("Счастливцев")
                .middleName("Александрович")
                .birthday(new Date(944611200000L))
                .institute(Institute.builder().id(1L).name("ВШ ИТИС").build())
                .faculty(Faculty.builder().id(1L).name("Программная инженерия").build())
                .group("11-804")
                .yearStart((short) 2018)
                .yearGraduate((short) 2020)
                .average((byte) 93)
                .phone("89503135579")
                .email("rodsher111@gmail.com")
                .bio("Гений<br>Гигант мысли<br>Отец русской демократии")
                .link("https://vk.com").build();
        student.setId(1L);
        List<StudentCompetence> competences = new ArrayList<>();
//        competences.add(new StudentCompetence(false, student, Competence.builder().id(1L).name("Java").build(), student.getId(), 1L));
//        competences.add(new StudentCompetence(false, student, Competence.builder().id(2L).name("Data mining").build(), student.getId(), 2L));
//        student.setCompetences(competences);
        Student me = Student.builder().build();
        me.setId(1L);
        map.put("me", me);
        map.put("student", student);
        return "main/student_page";
    }

    @GetMapping("/teacher")
    public String getTeacherPage(ModelMap map) {
        Teacher teacher = Teacher.builder()
                .name("Михаил")
                .surname("Счастливцев")
                .middleName("Александрович")
                .institute(Institute.builder().id(1L).name("ВШ ИТИС").build())
                .experience((byte) 30)
                .position("Основной работник, гений")
                .phone("89503135579")
                .email("rodsher111@gmail.com")
                .link("https://vk.com").build();
        teacher.setId(1L);
        List<Competence> competences = new ArrayList<>();
        competences.add(Competence.builder().id(1L).name("Java").build());
        competences.add(Competence.builder().id(2L).name("Data mining").build());
        teacher.setCompetence(competences);
        Teacher me = Teacher.builder().build();
        me.setId(1L);
        map.put("me", me);
        map.put("teacher", teacher);
        return "main/teacher_page";
    }

    @GetMapping("/employer")
    public String getEmployerPage(ModelMap map) {
        Employer employer = Employer.builder()
                .name("Михаил")
                .surname("Счастливцев")
                .middleName("Александрович")
                .phone("89503135579")
                .email("rodsher111@gmail.com")
                .companyName("ООО 'ГЕНИЕПРОИЗВОДСТВО'")
                .psrn("1231241352361").build();
        employer.setId(1L);
        Employer me = Employer.builder().build();
        me.setId(1L);
        map.put("me", me);
        map.put("employer", employer);
        return "main/employer_page";
    }

    @GetMapping("/administration")
    public String getAdministrationPage(ModelMap map) {
        Administration administration = new Administration(1L, "gggg", "123");
        Administration me = new Administration(1L, null, null);
        map.put("me", me);
        map.put("administration", administration);
        return "main/administration_page";
    }

    @GetMapping("/resume/create")
    public String getResumeCreateForm(ModelMap map) {
        List<String> competences = new ArrayList<>();
        competences.add("Программная инженерия");
        competences.add("Физика");
        competences.add("Биология");
        competences.add("Математика");
        map.put("competences", competences);
        return "main/resume_create";
    }

    public class Administration {
        private Long id;
        private String login;
        private String password;

        public Administration(Long id, String login, String password) {
            this.id = id;
            this.login = login;
            this.password = password;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}