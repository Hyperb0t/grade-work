package ru.itis.jaboderzhateli.gradework.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Vitaly
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String link;

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private Student student;
}
