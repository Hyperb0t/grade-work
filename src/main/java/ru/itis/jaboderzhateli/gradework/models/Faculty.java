package ru.itis.jaboderzhateli.gradework.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private List<Student> students;
}
