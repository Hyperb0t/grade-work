package ru.itis.jaboderzhateli.gradework.services.implementations;

import com.poiji.exception.PoijiExcelType;
import lombok.AllArgsConstructor;
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
import ru.itis.jaboderzhateli.gradework.services.interfaces.ConverterService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.SignUpService;
import ru.itis.jaboderzhateli.gradework.utils.excelLoader.FileToPOJOHandler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final EmployerRepository employerRepository;
    private final FacultyRepository facultyRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileToPOJOHandler poiHandler;
    private final ConverterService converterService;
    private final InstituteRepository instituteRepository;

    @Override
    public Employer signUp(SignUpEmployerForm form) {

        var employer = Employer.builder()
                .email(form.getEmail())
//                .bio(form.getLink().get(0))
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

        Institute institute;
        var instituteCandidate = instituteRepository.findByName(form.getInstitute());
        institute = instituteCandidate.orElseGet(() -> instituteRepository.save(Institute.builder()
                .name(form.getInstitute())
                .build()));

        Faculty faculty;
        var facultyCandidate = facultyRepository.findByName(form.getFaculty());
        faculty = facultyCandidate.orElseGet(() -> facultyRepository.save(Faculty.builder()
                .name(form.getFaculty())
                .build()));

        var student = Student.builder()
                .name(form.getName())
                .surname(form.getSurname())
                .middleName(form.getMiddleName())
                .login(form.getLogin())
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

        student.setRole(Role.STUDENT);

        return studentRepository.save(student);
    }

    @Override
    public Teacher signUp(SignUpTeacherForm form) {

        Institute institute;
        var instituteCandidate = instituteRepository.findByName(form.getInstitute());
        institute = instituteCandidate.orElseGet(() -> instituteRepository.save(Institute.builder()
                .name(form.getInstitute())
                .build()));

        var teacher = Teacher.builder()
//                .competence(form.getCompetence())
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
    public List<Student> signUpStudents(MultipartFile file) {

        Enum<?> header = parseFileExtension(file);

        try {
            var studentsTemp = poiHandler.upload(file.getInputStream(), StudentPoijiDto.class, header);
            var students = converterService.convertStudents(studentsTemp);

            return studentRepository.saveAll(students);

        } catch (IOException e) {
            return Collections.emptyList();
        }
    }


    //TODO validation
    @Override
    public List<Teacher> signUpTeachers(MultipartFile file) {

        Enum<?> header = parseFileExtension(file);

        try {
            var teacherTemp = poiHandler.upload(file.getInputStream(), TeacherPoijiDto.class, header);
            var teachers = converterService.convertTeachers(teacherTemp);

            return teacherRepository.saveAll(teachers);

        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private Enum<?> parseFileExtension(MultipartFile file) {
        var fileName = file.getOriginalFilename();
        var filenamePostfix = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf('.'));

        //TODO Review код

        Enum<?> header;

        switch (filenamePostfix) {
            case ("xlsx"):
                header = PoijiExcelType.XLSX;
                break;
//            case ("xls"):
//                header = PoijiExcelType.XLS;
//                break;
            default:
                header = PoijiExcelType.XLS;
                break;
        }
        return header;
    }

}
