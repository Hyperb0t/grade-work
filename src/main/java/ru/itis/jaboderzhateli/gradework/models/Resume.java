//package ru.itis.jaboderzhateli.gradework.models;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import lombok.experimental.SuperBuilder;
//
//import javax.persistence.*;
//import java.util.List;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@SuperBuilder
//@Entity
//public class Resume {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String phone;
//    private String email;
//    private String bio;
//
//    @JsonBackReference
//    @ToString.Exclude
//    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
//    private List<Project> projects;
//
//    @OneToMany(mappedBy = "resume")
//    @ToString.Exclude
//    private List<StudentCompetence> competences;
//
//}
