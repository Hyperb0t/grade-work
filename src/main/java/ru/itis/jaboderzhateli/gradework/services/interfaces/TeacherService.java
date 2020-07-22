package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.Teacher;
import ru.itis.jaboderzhateli.gradework.models.User;

import java.util.Map;
import java.util.Optional;

public interface TeacherService {

    Teacher edit(Teacher teacher, Map<String, String> params);

    Teacher edit(User user, Map<String, String> params);

    Teacher get(Long id);

}
