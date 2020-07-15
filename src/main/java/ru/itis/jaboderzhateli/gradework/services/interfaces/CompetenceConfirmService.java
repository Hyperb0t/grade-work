package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.StudentCompetence;
import ru.itis.jaboderzhateli.gradework.models.Teacher;

import java.util.List;
import java.util.Map;

public interface CompetenceConfirmService {
    public List<StudentCompetence> getCompetencesToConfirm(Long teacherId);

    public List<StudentCompetence> getCompetencesToConfirm(Teacher teacher);

    public void confirmFromRequest(Map<String, String> params);

    public void confirmCompetence(StudentCompetence studentCompetence);

    public void deleteCompetence(StudentCompetence studentCompetence);
}
