package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpEmployerForm;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpStudentForm;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpTeacherForm;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.StudentPoijiDto;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.TeacherPoijiDto;
import ru.itis.jaboderzhateli.gradework.models.*;
import ru.itis.jaboderzhateli.gradework.repositories.*;
import ru.itis.jaboderzhateli.gradework.services.interfaces.*;
import ru.itis.jaboderzhateli.gradework.utils.excelLoader.ExtensionParser;
import ru.itis.jaboderzhateli.gradework.utils.excelLoader.ExcelConverter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final EmployerRepository employerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ExcelConverter poiHandler;
    private final ConverterService converterService;
    private final CompetenceService competenceService;
    private final FacultyService facultyService;
    private final InstituteService instituteService;
    private final ExtensionParser extensionParser;
    private final PasswordGenerationService passwordGenerationService;

    @Override
    public Employer signUp(SignUpEmployerForm form) {

        var bio = String.join("<br>", form.getLink());

        var employer = Employer.builder()
                .email(form.getEmail())
                .bio(bio)
                .login(form.getLogin())
                .name(form.getName())
                .companyName(form.getOrganisationName())
                .password(passwordEncoder.encode(form.getPassword()))
                .phone(form.getPhone())
                .psrn(form.getPsrn())
                .surname(form.getSurname())
                .middleName(form.getMiddleName())
                .build();

        employer.setRole(Role.EMPLOYER);

        return employerRepository.save(employer);
    }

    @Override
    public Student signUp(SignUpStudentForm form) {

        Institute institute = instituteService.getInstitute(form.getInstitute());
        Faculty faculty = facultyService.getFaculty(form.getFaculty());

        var student = Student.builder()
                .name(form.getName())
                .phone(form.getPhone())
                .surname(form.getSurname())
                .middleName(form.getMiddleName())
                .login(form.getLogin())
                .email(form.getLogin())
                .link(form.getLink())
                .institute(institute)
                .group(form.getGroup())
                .faculty(faculty)
                .average(form.getAverage())
                .yearStart(form.getYearStart())
                .yearGraduate(form.getYearGraduate())
                .birthday(form.getBirth())
                .password(passwordEncoder.encode(form.getPassword()))
                .build();
        log.info("registering student from admin with pass: " + form.getPassword());
        student.setRole(Role.STUDENT);

        return studentRepository.save(student);
    }

    @Override
    public Teacher signUp(SignUpTeacherForm form) {

        var institute = instituteService.getInstitute(form.getInstitute());

        var competences = form.getCompetence().stream()
                .map(competenceService::getCompetence)
                .collect(Collectors.toList());

        var teacher = Teacher.builder()
                .competence(competences)
                .institute(institute)
                .link(form.getLink())
                .login(form.getLogin())
                .middleName(form.getMiddleName())
                .name(form.getName())
                .password(passwordEncoder.encode(form.getPassword()))
                .position(form.getPosition())
                .surname(form.getSurname())
                .experience(form.getExperience())
                .build();

        teacher.setRole(Role.TEACHER);

        return teacherRepository.save(teacher);
    }

    @Override
    public List<Map<Student, String>> signUpStudents(MultipartFile file) {

        Enum<?> header = extensionParser.parseFileExtension(file);

        try {
            var studentsTemp = poiHandler.upload(file.getInputStream(), StudentPoijiDto.class, header);
            var students = converterService.convertStudents(studentsTemp);
            Map<Student, String> map = new HashMap<>();

            students.forEach(student -> {
                var password = passwordGenerationService.generate();
                student.setPassword(passwordEncoder.encode(password));
                map.put(student,password);
            });

            studentRepository.saveAll(students);
            return Collections.singletonList(map);

        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Map<Teacher, String>> signUpTeachers(MultipartFile file) {

        Enum<?> header = extensionParser.parseFileExtension(file);

        try {
            var teacherTemp = poiHandler.upload(file.getInputStream(), TeacherPoijiDto.class, header);
            var teachers = converterService.convertTeachers(teacherTemp);

            Map<Teacher, String> map = new HashMap<>();

            teachers.forEach(teacher -> {
                var password = passwordGenerationService.generate();
                teacher.setPassword(passwordEncoder.encode(password));
                map.put(teacher,password);
            });

            teacherRepository.saveAll(teachers);
            return Collections.singletonList(map);

        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

}
