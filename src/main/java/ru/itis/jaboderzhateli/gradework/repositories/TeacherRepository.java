package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}
