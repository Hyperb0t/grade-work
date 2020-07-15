package ru.itis.jaboderzhateli.gradework.models;

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
public class Employer extends User {

    private String bio; //they write links and other stuff here
    private String psrn;

    @Column(length = 50)
    private String companyName;
    @Column(length = 50)
    private String email;
    @Column(length = 13)
    private String phone;

    @Column(length = 40)
    private String name;
    @Column(length = 40)
    private String middleName;
    @Column(length = 40)
    private String surname;

    @OneToMany(mappedBy = "employer")
    @ToString.Exclude
    private List<JobApplication> jobApplications;
}
