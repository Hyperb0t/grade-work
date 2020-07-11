package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
