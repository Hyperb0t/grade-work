package ru.itis.jaboderzhateli.gradework.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.models.Institute;
import ru.itis.jaboderzhateli.gradework.models.Student;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstituteDto {

    private String name;

    private List<Student> students;

    public static InstituteDto from(Institute institute) {
        return InstituteDto.builder()
                .name(institute.getName())
                .students(institute.getStudents())
                .build();
    }
}
