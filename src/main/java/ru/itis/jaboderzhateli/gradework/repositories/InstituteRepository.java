package ru.itis.jaboderzhateli.gradework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.jaboderzhateli.gradework.models.Institute;

import java.util.Optional;

@Repository
public interface InstituteRepository extends CrudRepository<Institute, Long> {

    Optional<Institute> findByName(String name);

}
