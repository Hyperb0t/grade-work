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
public class StudentCompetence {

    @EmbeddedId
    private StudentCompetenceKey id;

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn
    private Student student;

    @ManyToOne
    @MapsId("competence_id")
    @JoinColumn
    private Competence competence;

    private Boolean confirmed;

    @Embeddable
    public class StudentCompetenceKey implements Serializable {
        @Column(name = "student_id")
        private Long studentId;
        @Column(name = "competence_id")
        private Long competenceId;
    }
}
