package ru.itis.jaboderzhateli.gradework.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.JobApplication;
import ru.itis.jaboderzhateli.gradework.models.Role;

import java.util.List;

@Data
@Builder
public class EmployerDto extends UserDto {

    private String bio;
    private String psrn;
    private String companyName;
    private String email;
    private String phone;

    private List<JobApplication> jobApplications;

    public static EmployerDto from(Employer employer) {
        var tempEmployerDto = EmployerDto.builder()
                .bio(employer.getBio())
                .psrn(employer.getPsrn())
                .companyName(employer.getCompanyName())
                .email(employer.getEmail())
                .phone(employer.getPhone())
                .jobApplications(employer.getJobApplications())
                .build();
        tempEmployerDto.setLogin(employer.getLogin());
        tempEmployerDto.setRole(Role.EMPLOYER);
        return tempEmployerDto;
    }

}
