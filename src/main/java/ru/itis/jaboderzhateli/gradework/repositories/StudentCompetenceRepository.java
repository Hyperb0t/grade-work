package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.Competence;
import ru.itis.jaboderzhateli.gradework.models.StudentCompetence;
import ru.itis.jaboderzhateli.gradework.models.StudentCompetenceId;
import ru.itis.jaboderzhateli.gradework.models.Teacher;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCompetenceRepository extends JpaRepository<StudentCompetence, StudentCompetenceId> {
    List<StudentCompetence> findAllByConfirmedIsAndCompetence_Teachers(Boolean confirmed, Teacher teacher);
    Optional<StudentCompetence> findByStudentIdAndCompetenceId(Long studentId, Long competenceId);
    List<StudentCompetence> findAllByConfirmedIsAndCompetenceIn(Boolean confirmed, List<Competence> competences);
}
