package ru.itis.jaboderzhateli.gradework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Vitaly
 * This class is made as a separate entity for possibility of confirmation,
 * that student owns a comptence. Watch boolean "confirmed" field.
 * Also useful to read: https://stackoverflow.com/questions/23837561/jpa-2-0-many-to-many-with-extra-column.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student_competence")
@IdClass(StudentCompetenceKey.class)
public class StudentCompetence {

    private Boolean confirmed;

    @Transient
    private Student student;
    @Transient
    private Competence competence;

    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Id
    @Column(name = "competence_id")
    private Long competenceId;
}
