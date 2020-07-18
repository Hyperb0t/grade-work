package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.Competence;
import ru.itis.jaboderzhateli.gradework.models.Faculty;
import ru.itis.jaboderzhateli.gradework.models.Institute;
import ru.itis.jaboderzhateli.gradework.models.Student;

import java.util.List;
import java.util.Map;

public interface SearchService {

    List<Student> findFromRequest(Map<String, String> params);
}
