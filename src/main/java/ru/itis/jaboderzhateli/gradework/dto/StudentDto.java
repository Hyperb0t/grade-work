package ru.itis.jaboderzhateli.gradework.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.models.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
public class StudentDto extends UserDto {

    private Short yearStart;
    private Short yearGraduate;
    private Byte course;
    private String bio;
    private String name;
    private String surname;
    private String group;
    private String email;
    private String phone;

    private Institute institute;

    private List<StudentCompetence> studentCompetences;
    private List<Project> projects;
    private List<JobApplication> jobApplications;

    public static StudentDto from(Student student) {
        var tempStudentDto = StudentDto.builder()
                .yearStart(student.getYearStart())
                .yearGraduate(student.getYearGraduate())
                .course(student.getCourse())
                .bio(student.getBio())
                .name(student.getName())
                .surname(student.getSurname())
                .group(student.getGroup())
                .email(student.getEmail())
                .phone(student.getPhone())
                .institute(student.getInstitute())
                .studentCompetences(student.getStudentCompetences())
                .projects(student.getProjects())
                .jobApplications(student.getJobApplications())
                .build();
        tempStudentDto.setLogin(student.getLogin());
        tempStudentDto.setRole(Role.STUDENT);
        return tempStudentDto;
    }
}
