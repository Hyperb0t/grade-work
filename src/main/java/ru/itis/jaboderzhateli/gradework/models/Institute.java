package ru.itis.jaboderzhateli.gradework.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<Student> students;
}
