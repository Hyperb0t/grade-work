package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.JobApplication;
import ru.itis.jaboderzhateli.gradework.models.Student;

import java.util.List;

public interface ApplicationService {
    void apply(Student student, Employer employer);
    List<JobApplication> getApplications(Employer employer, Boolean read);
    void setRead(JobApplication jobApplication, Boolean read);
}
