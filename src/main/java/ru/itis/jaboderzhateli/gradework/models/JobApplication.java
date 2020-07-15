package ru.itis.jaboderzhateli.gradework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

/**
 * @author Vitaly
 * Entity, describing a request that student sends to employer to apply for a job.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean read;
    private Instant sentAt;

    @ManyToOne
    @JoinColumn
    private Student student;

    @ManyToOne
    @JoinColumn
    private Employer employer;
}
