package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.User;

import java.util.Map;
import java.util.Optional;

public interface StudentService {

    Student edit(Student student, Map<String, String> params);

    Student edit(User user, Map<String, String> params);

    Student get(Long id);

}
