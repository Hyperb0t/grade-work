package ru.itis.jaboderzhateli.gradework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.models.Competence;
import ru.itis.jaboderzhateli.gradework.models.StudentCompetence;
import ru.itis.jaboderzhateli.gradework.models.Teacher;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceDto {

    private String name;
    private List<Teacher> teachers;
    private List<StudentCompetence> studentCompetences;

    public static CompetenceDto from(Competence competence) {
        return CompetenceDto.builder()
                .name(competence.getName())
                .studentCompetences(competence.getStudentCompetences())
                .teachers(competence.getTeachers())
                .build();
    }
}
