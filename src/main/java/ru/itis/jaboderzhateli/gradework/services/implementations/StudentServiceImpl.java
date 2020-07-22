package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.User;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.repositories.UserRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.FacultyService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.InstituteService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ResumeService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.StudentService;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final InstituteService instituteService;
    private final FacultyService facultyService;
    private final ResumeService resumeService;

    @Override
    public Student edit(Student student, Map<String, String> params) {
        return null;
    }

    @Override
    public Student edit(User user, Map<String, String> params) {

        var student = studentRepository.getOne(user.getId());

        if (!params.get("bio").trim().equals(""))
            student.setBio(params.get("bio").replaceAll("\\r\\n", "<br>"));

        student.setInstitute(instituteService.getInstitute(params.get("institute")));
        student.setAverage(Byte.valueOf(params.get("average")));
        student.setBirthday(Date.from(Instant.parse(params.get("birthday"))));
        student.setFaculty(facultyService.getFaculty(params.get("faculty")));
        student.setCourse(Byte.valueOf(params.get("course")));
        student.setGroup(params.get("group"));
        student.setLink(params.get("link"));
        student.setName(params.get("name"));
        student.setMiddleName(params.get("middleName"));
        student.setSurname(params.get("surname"));
        student.setYearGraduate(Short.valueOf(params.get("yearStart")));
        student.setYearStart(Short.valueOf(params.get("yearGraduate")));

        return studentRepository.save(student);

    }

    @Override
    public Student get(Long id) {
        return null;
    }
}
