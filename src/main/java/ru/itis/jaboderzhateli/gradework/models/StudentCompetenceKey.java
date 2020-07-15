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
    private Long studentId;
    private Long competenceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCompetenceKey that = (StudentCompetenceKey) o;
        return studentId.equals(that.studentId) &&
                competenceId.equals(that.competenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, competenceId);
    }
}
