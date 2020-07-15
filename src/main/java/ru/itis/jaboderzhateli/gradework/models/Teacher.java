package ru.itis.jaboderzhateli.gradework.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * @author Vitaly
 * @author Zagir
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Teacher extends User {

    @Column(length = 40)
    private String name;
    @Column(length = 40)
    private String middleName;
    @Column(length = 40)
    private String surname;
    @Column(length = 50)
    private String email;
    @Column(length = 13)
    private String phone;
    private Byte experience;
    @Column(length = 50)
    private String position;
    private String link;

    @ManyToOne
    @JsonManagedReference
    private Institute institute;

    @ManyToMany
    @JoinTable
    @ToString.Exclude
    private List<Competence> competence;

}
