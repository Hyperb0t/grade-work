package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
}
