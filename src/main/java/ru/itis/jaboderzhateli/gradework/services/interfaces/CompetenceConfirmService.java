package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.StudentCompetence;
import ru.itis.jaboderzhateli.gradework.models.Teacher;

import java.util.List;
import java.util.Map;

public interface CompetenceConfirmService {
    List<StudentCompetence> getCompetencesToConfirm(Long teacherId);

    List<StudentCompetence> getCompetencesToConfirm(Teacher teacher);

    void confirmFromRequest(Map<String, String> params);

    void confirmCompetence(StudentCompetence studentCompetence);

    void deleteCompetence(StudentCompetence studentCompetence);
}
