package ru.itis.jaboderzhateli.gradework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Vitaly
 * Entity for using with spring-security. Student, Teacher and Employer are bound
 * (have a link column) to it.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
