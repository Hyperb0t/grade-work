package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.jaboderzhateli.gradework.models.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
