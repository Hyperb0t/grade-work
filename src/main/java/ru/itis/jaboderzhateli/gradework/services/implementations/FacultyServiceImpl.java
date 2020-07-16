package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.Faculty;
import ru.itis.jaboderzhateli.gradework.repositories.FacultyRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.FacultyService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty getFaculty(String facultyName) {
        Faculty faculty;
        var facultyCandidate = facultyRepository.findByName(facultyName);
        faculty = facultyCandidate.orElseThrow(() -> new IllegalArgumentException(
                "Faculty with specified name " + facultyName + " does not exist"));
        return faculty;
    }
}
