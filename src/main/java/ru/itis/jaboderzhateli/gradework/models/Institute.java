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
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private List<Student> students;

    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private List<Teacher> teachers;
}
