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
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.Role;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.Teacher;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.repositories.TeacherRepository;
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
    private final PasswordEncoder passwordEncoder;
    private final FileToPOJOHandler poiHandler;
    private final ConverterService converterService;

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
                .build();

        employer.setRole(Role.EMPLOYER);

        return employerRepository.save(employer);
    }

    @Override
    public Student signUp(SignUpStudentForm form) {

        var student = Student.builder()
                .build();

        return studentRepository.save(student);
    }

    @Override
    public Teacher signUp(SignUpTeacherForm form) {

        var teacher = Teacher.builder().build();

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
