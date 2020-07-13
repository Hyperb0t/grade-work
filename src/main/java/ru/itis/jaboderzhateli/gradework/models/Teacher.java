package ru.itis.jaboderzhateli.gradework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author Vitaly
 * @author Zagir
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Teacher extends User {

    @Column(length = 40)
    private String name;
    @Column(length = 40)
    private String surname;
    @Column(length = 50)
    private String email;
    @Column(length = 13)
    private String phone;

    @ManyToMany
    @JoinTable
    private List<Competence> competence;

}
