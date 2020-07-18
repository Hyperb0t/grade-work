package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.dto.StudentDto;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.StudentPoijiDto;
import ru.itis.jaboderzhateli.gradework.dto.poijiDto.TeacherPoijiDto;
import ru.itis.jaboderzhateli.gradework.models.*;
import ru.itis.jaboderzhateli.gradework.repositories.FacultyRepository;
import ru.itis.jaboderzhateli.gradework.repositories.InstituteRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ConverterService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConverterServiceImpl implements ConverterService {

    private final InstituteRepository instituteRepository;
    private final FacultyRepository facultyRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Student convert(StudentPoijiDto studentDto) {

        var instituteCandidate = instituteRepository.findByName(studentDto.getInstitute());
        Institute institute = instituteCandidate.orElseGet(() -> Institute.builder()
                .name(studentDto.getInstitute())
                .build());
        var facultyCandidate = facultyRepository.findByName(studentDto.getFaculty());
        Faculty faculty = facultyCandidate.orElseGet(() -> Faculty.builder()
                .name(studentDto.getFaculty())
                .build());

        var student = Student.builder()
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .middleName(studentDto.getMiddleName())
                .login(studentDto.getLogin())
                .link(studentDto.getLink())
                .institute(institute)
                .group(studentDto.getGroup())
                .faculty(faculty)
                .course(studentDto.getCourse().byteValue())
                .email(studentDto.getEmail())
                .bio(studentDto.getBio())
                .phone(studentDto.getPhone())
                .average(studentDto.getAverage().byteValue())
                .yearStart(studentDto.getYearStart().shortValue())
                .yearGraduate(studentDto.getYearGraduate().shortValue())
                .birthday(studentDto.getBirth())
                .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                .build();

        student.setLogin(studentDto.getLogin());
        student.setRole(Role.STUDENT);

        return student;
    }

    @Override
    public Teacher convert(TeacherPoijiDto teacherDto) {

        var instituteCandidate = instituteRepository.findByName(teacherDto.getInstitute());
        Institute institute = instituteCandidate.orElseGet(() -> Institute.builder()
                .name(teacherDto.getInstitute())
                .build());

        var teacher = Teacher.builder()
                .email(teacherDto.getEmail())
                .institute(institute)
                .experience(teacherDto.getExperience().byteValue())
                .link(teacherDto.getLink())
                .position(teacherDto.getPosition())
                .name(teacherDto.getName())
                .phone(teacherDto.getPhone())
                .surname(teacherDto.getSurname())
                .middleName(teacherDto.getMiddleName())
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
