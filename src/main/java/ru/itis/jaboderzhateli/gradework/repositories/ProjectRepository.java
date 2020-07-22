package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.jaboderzhateli.gradework.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Transactional
    void deleteByStudent_Id(Long studentId);
}
