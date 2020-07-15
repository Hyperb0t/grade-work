package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.StudentCompetence;
import ru.itis.jaboderzhateli.gradework.models.StudentCompetenceKey;

@Repository
public interface StudentCompetenceRepository extends CrudRepository<StudentCompetence, StudentCompetenceKey> {
}
