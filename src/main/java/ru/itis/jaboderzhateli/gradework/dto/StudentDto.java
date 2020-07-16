package ru.itis.jaboderzhateli.gradework.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.jaboderzhateli.gradework.models.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class StudentDto extends UserDto {

    private Short yearStart;
    private Short yearGraduate;
    private Byte course;
    private String bio;
    private String name;
    private String middleName;
    private String surname;
    private String group;
    private String email;
    private String phone;
    private Date birthday;
    private Byte average;
    private String link;

    private Institute institute;

    private Faculty faculty;

    private List<StudentCompetence> competences;
    private List<Project> projects;
    private List<JobApplication> jobApplications;

    public static StudentDto from(Student student) {

        var tempStudentDto = StudentDto.builder()
                .yearStart(student.getYearStart())
                .yearGraduate(student.getYearGraduate())
                .course(student.getCourse())
                .bio(student.getBio())
                .name(student.getName())
                .middleName(student.getMiddleName())
                .surname(student.getSurname())
                .group(student.getGroup())
                .email(student.getEmail())
                .phone(student.getPhone())
                .birthday(student.getBirthday())
                .average(student.getAverage())
                .link(student.getLink())
                .institute(student.getInstitute())
                .faculty(student.getFaculty())
                .competences(student.getCompetences())
                .projects(student.getProjects())
                .jobApplications(student.getJobApplications())
                .build();

        tempStudentDto.setLogin(student.getLogin());
        tempStudentDto.setRole(Role.STUDENT);

        return tempStudentDto;
    }
}
