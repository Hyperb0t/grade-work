package ru.itis.jaboderzhateli.gradework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCompetenceId implements Serializable {
    private Long student;
    private Long competence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCompetenceId that = (StudentCompetenceId) o;
        return student.equals(that.student) &&
                competence.equals(that.competence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, competence);
    }
}
