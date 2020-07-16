package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyService {

    List<Faculty> getAllFaculties();

    Faculty getFaculty(String facultyName);

}
