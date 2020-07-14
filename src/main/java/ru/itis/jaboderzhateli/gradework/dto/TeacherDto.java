package ru.itis.jaboderzhateli.gradework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.models.Competence;
import ru.itis.jaboderzhateli.gradework.models.Role;
import ru.itis.jaboderzhateli.gradework.models.Teacher;

import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Builder
public class TeacherDto extends UserDto {

    private String name;
    private String middleName;
    private String surname;
    private String email;
    private String phone;

    private List<Competence> competence;

    public static TeacherDto from(Teacher teacher) {
        var tempTeacherDto = TeacherDto.builder()
                .name(teacher.getName())
                .middleName(teacher.getMiddleName())
                .surname(teacher.getSurname())
                .email(teacher.getEmail())
                .phone(teacher.getPhone())
                .build();
        tempTeacherDto.setLogin(teacher.getLogin());
        tempTeacherDto.setRole(Role.TEACHER);
        return tempTeacherDto;
    }
}
