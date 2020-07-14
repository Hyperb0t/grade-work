package ru.itis.jaboderzhateli.gradework.services.implementations;

import com.poiji.exception.PoijiExcelType;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpEmployerForm;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.StudentPoijiDto;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.TeacherPoijiDto;
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.Role;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.repositories.TeacherRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ConverterService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.SignUpService;
import ru.itis.jaboderzhateli.gradework.utils.excelLoader.FileToPOJOHandler;

import java.io.IOException;
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
    public void signUp(SignUpEmployerForm form) {

        var employer = Employer.builder()
                .bio(form.getBio())
                .companyName(form.getCompanyName())
                .email(form.getEmail())
                .juristicInfo(form.getJuristicInfo())
                .phone(form.getPhone())
                .build();

        employer.setLogin(form.getLogin());
        employer.setPassword(passwordEncoder.encode(form.getPassword()));
        employer.setRole(Role.EMPLOYER);

        employerRepository.save(employer);
    }

    @Override
    public void signUpStudent(MultipartFile file) {

        Enum<?> header = parseFileExtension(file);

        try {
            var studentsTemp = poiHandler.upload(file.getInputStream(), StudentPoijiDto.class, header);
            var students = converterService.convertStudents(studentsTemp);

            studentRepository.saveAll(students);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void signUpTeacher(MultipartFile file) {

        Enum<?> header = parseFileExtension(file);

        try {
            var teacherTemp = poiHandler.upload(file.getInputStream(), TeacherPoijiDto.class, header);
            var teachers = converterService.convertTeachers(teacherTemp);

            teacherRepository.saveAll(teachers);

        } catch (IOException e) {
            e.printStackTrace();
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
