package ru.itis.jaboderzhateli.gradework.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Vitaly
 * This class is made as a separate entity for possibility of confirmation,
 * that student owns a comptence. Watch boolean "confirmed" field.
 * Also useful to read: https://stackoverflow.com/questions/23837561/jpa-2-0-many-to-many-with-extra-column.
 */

//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student_competence")
@IdClass(StudentCompetenceId.class)
public class StudentCompetence {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;

    @Column
    private Boolean confirmed;
}
