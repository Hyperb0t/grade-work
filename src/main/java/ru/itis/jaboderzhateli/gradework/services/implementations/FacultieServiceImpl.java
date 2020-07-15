package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.Faculty;
import ru.itis.jaboderzhateli.gradework.repositories.FacultyRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.FacultieService;

import java.util.List;

@Service
@AllArgsConstructor
public class FacultieServiceImpl implements FacultieService {

    private final FacultyRepository facultyRepository;

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
}
