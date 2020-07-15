package ru.itis.jaboderzhateli.gradework.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
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
public class Student extends User{

    private Short yearStart;
    private Short yearGraduate;
    private Byte course;
    private String bio;

    @Column(length = 40)
    private String name;
    @Column(length = 40)
    private String middleName;
    @Column(length = 40)
    private String surname;
    @Column(length = 10, name = "study_group")
    private String group;
    @Column(length = 50)
    private String email;
    @Column(length = 13)
    private String phone;
    private Date birthday;
    private Byte average;
    private String link;

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private Institute institute;

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private Faculty faculty;

    @OneToMany(mappedBy = "student")
    private List<StudentCompetence> competences;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Project> projects;
    @OneToMany(mappedBy = "student")
    private List<JobApplication> jobApplications;

}
