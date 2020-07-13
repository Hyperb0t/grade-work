package ru.itis.jaboderzhateli.gradework.models;

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
public class Employer extends User {

    @Column(length = 50)
    private String companyName;
    private String bio;
    private String juristicInfo;
    @Column(length = 50)
    private String email;
    @Column(length = 13)
    private String phone;
    @OneToMany(mappedBy = "employer")
    private List<JobApplication> jobApplications;
}
