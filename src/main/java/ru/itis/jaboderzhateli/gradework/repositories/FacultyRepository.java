package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.jaboderzhateli.gradework.models.Faculty;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Optional<Faculty> findByName(String name);

}
