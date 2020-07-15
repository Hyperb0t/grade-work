package ru.itis.jaboderzhateli.gradework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCompetenceKey implements Serializable {
    private Student student;
    private Competence competence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCompetenceKey that = (StudentCompetenceKey) o;
        return student.equals(that.student) &&
                competence.equals(that.competence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, competence);
    }
}
