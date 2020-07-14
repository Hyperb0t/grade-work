package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.dto.StudentDto;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.StudentPoijiDto;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.TeacherPoijiDto;
import ru.itis.jaboderzhateli.gradework.models.Institute;
import ru.itis.jaboderzhateli.gradework.models.Role;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.Teacher;
import ru.itis.jaboderzhateli.gradework.repositories.InstituteRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ConverterService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConverterServiceImpl implements ConverterService {

    private final InstituteRepository instituteRepository;

    @Override
    public Student convert(StudentPoijiDto studentDto) {

        var instituteCandidate = instituteRepository.findByName(studentDto.getInstitute());
        Institute institute = instituteCandidate.orElseGet(() -> Institute.builder()
                .name(studentDto.getInstitute())
                .build());

        var student = Student.builder()
                .yearStart(studentDto.getYearStart().shortValue())
                .yearGraduate(studentDto.getYearGraduate().shortValue())
                .course(studentDto.getCourse().byteValue())
                .bio(studentDto.getBio())
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .group(studentDto.getGroup())
                .email(studentDto.getEmail())
                .phone(studentDto.getPhone())
                .institute(institute)
                .build();

        student.setLogin(studentDto.getLogin());
        student.setRole(Role.STUDENT);

        return student;
    }

    @Override
    public Teacher convert(TeacherPoijiDto teacherDto) {
        var teacher = Teacher.builder()
                .email(teacherDto.getEmail())
                .name(teacherDto.getName())
                .phone(teacherDto.getPhone())
                .surname(teacherDto.getSurname())
                .build();

        teacher.setLogin(teacherDto.getLogin());
        teacher.setRole(Role.TEACHER);
        return teacher;
    }

    @Override
    public List<Student> convertStudents(List<StudentPoijiDto> studentDtos) {
        return studentDtos.stream()
                .map((this::convert))
                .collect(Collectors.toList());
    }

    @Override
    public List<Teacher> convertTeachers(List<TeacherPoijiDto> teacherDtos) {
        return teacherDtos.stream()
                .map((this::convert))
                .collect(Collectors.toList());
    }

}
