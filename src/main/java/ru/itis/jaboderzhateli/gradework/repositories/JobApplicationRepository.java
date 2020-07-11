package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.JobApplication;

@Repository
public interface JobApplicationRepository extends CrudRepository<JobApplication, Long> {
}
