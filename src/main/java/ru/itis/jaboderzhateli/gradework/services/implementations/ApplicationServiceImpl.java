package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.JobApplication;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.repositories.JobApplicationRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ApplicationService;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final JobApplicationRepository applicationRepository;

    @Override
    public void apply(Student student, Employer employer) {
        JobApplication application = JobApplication.builder()
                .student(student)
                .employer(employer)
                .read(false)
                .sentAt(Instant.now())
                .build();

        applicationRepository.save(application);
    }

    @Override
    public List<JobApplication> getApplications(Employer employer, Boolean read) {
        return applicationRepository.findAllByEmployerAndReadIs(employer, read);
    }

    @Override
    public void setRead(JobApplication jobApplication, Boolean read) {
        jobApplication.setRead(read);
        applicationRepository.save(jobApplication);
    }
}
