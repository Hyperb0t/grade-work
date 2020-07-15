package ru.itis.jaboderzhateli.gradework.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author Vitaly
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Competence{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "competence")
    @ToString.Exclude
    private List<StudentCompetence> studentCompetences;

    @ManyToMany(mappedBy = "competence", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Teacher> teachers;
}
