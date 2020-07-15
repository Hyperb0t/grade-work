package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.Competence;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
}
