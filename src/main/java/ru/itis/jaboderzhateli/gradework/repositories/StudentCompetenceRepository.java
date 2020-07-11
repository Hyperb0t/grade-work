package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.StudentCompetence;

@Repository
public interface StudentCompetenceRepository extends CrudRepository<StudentCompetence, StudentCompetence.StudentCompetenceKey> {
}
