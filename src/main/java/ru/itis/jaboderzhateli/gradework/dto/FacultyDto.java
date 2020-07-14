package ru.itis.jaboderzhateli.gradework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.models.Faculty;
import ru.itis.jaboderzhateli.gradework.models.Student;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDto {

    private String name;

    private List<Student> students;

    public static FacultyDto from(Faculty faculty) {
        return FacultyDto.builder()
                .name(faculty.getName())
                .students(faculty.getStudents())
                .build();
    }
}
