package ru.itis.jaboderzhateli.gradework.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40)
    private String name;
    @Column(length = 40)
    private String surname;
    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private Institute institute;
    private Byte course;
    @Column(length = 10, name = "study_group")
    private String group;
    private Short yearStart;
    private Short yearGraduate;
    @Column(length = 50)
    private String email;
    @Column(length = 13)
    private String phone;
    private String bio;
    @OneToMany(mappedBy = "student")
    private List<StudentCompetence> studentCompetences;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Project> projects;
    @OneToMany(mappedBy = "student")
    private List<JobApplication> jobApplications;
    @OneToOne
    private User user;
}
