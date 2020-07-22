package ru.itis.jaboderzhateli.gradework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Vitaly
 * @author Zagir
 * Entity for using with spring-security. Student, Teacher and Employer are bound
 * (have a link column) to it.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String login;
    protected String password;

    @Enumerated(EnumType.STRING)
    protected Role role;
}
