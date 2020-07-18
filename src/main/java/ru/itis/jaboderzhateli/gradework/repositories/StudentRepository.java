package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.*;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByCompetencesIn(List<StudentCompetence> competences);

    List<Student> findAllByCompetencesInAndInstituteId(List<Competence> competences, Long instituteId);

    List<Student> findAllByCompetencesInAndFacultyId(List<Competence> competences, Long facultyId);

    List<Student> findAllByCompetencesInAndInstituteIdAndFacultyId(List<Competence> competences, Long instituteId, Long facultyId);

    List<Student> findAllByInstituteIdAndFacultyId(Long instituteId, Long facultyId);

    List<Student> findAllByInstituteId(Long instituteId);

    List<Student> findAllByFacultyId(Long facultyId);
}
