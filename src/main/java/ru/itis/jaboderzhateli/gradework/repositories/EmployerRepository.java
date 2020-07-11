package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.Employer;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Long> {
}
