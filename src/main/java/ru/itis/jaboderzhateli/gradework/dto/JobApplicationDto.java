package ru.itis.jaboderzhateli.gradework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.JobApplication;
import ru.itis.jaboderzhateli.gradework.models.Student;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDto {

    private Boolean read;
    private Instant sentAt;
    private Student student;
    private Employer employer;

    public static JobApplicationDto from(JobApplication jobApplication) {
        return JobApplicationDto.builder()
                .read(jobApplication.getRead())
                .sentAt(jobApplication.getSentAt())
                .student(jobApplication.getStudent())
                .employer(jobApplication.getEmployer())
                .build();
    }
}
