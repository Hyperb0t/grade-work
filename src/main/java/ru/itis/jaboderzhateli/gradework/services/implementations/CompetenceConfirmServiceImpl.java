package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.StudentCompetence;
import ru.itis.jaboderzhateli.gradework.models.StudentCompetenceId;
import ru.itis.jaboderzhateli.gradework.models.Teacher;
import ru.itis.jaboderzhateli.gradework.repositories.StudentCompetenceRepository;
import ru.itis.jaboderzhateli.gradework.repositories.TeacherRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.CompetenceConfirmService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Vitaly
 */

@Service
@Slf4j
public class CompetenceConfirmServiceImpl implements CompetenceConfirmService {

    @Autowired
    private StudentCompetenceRepository competenceRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<StudentCompetence> getCompetencesToConfirm(Long teacherId) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (teacherOptional.isPresent()) {
            return getCompetencesToConfirm(teacherOptional.get());
        } else throw new IllegalStateException("Teacher with id " + teacherId + " not found");
    }

    @Override
    public List<StudentCompetence> getCompetencesToConfirm(Teacher teacher) {
        return competenceRepository.findAllByConfirmedIsAndCompetence_Teachers(false, teacher);
    }

    @Override
    public void confirmFromRequest(Map<String, String> params) {
        for (Map.Entry<String, String> e : params.entrySet()) {
            if (e.getKey().matches("[0-9]*_[0-9]*")) {
                String temp[] = e.getKey().split("_");
                log.info("key array of ids: " + Arrays.toString(temp));
                Long studentId = Long.parseLong(temp[0]);
                Long competenceId = Long.parseLong(temp[1]);
                Optional<StudentCompetence> competenceOptional =
                        competenceRepository.findByStudentIdAndCompetenceId(studentId, competenceId);
                if (competenceOptional.isPresent()) {
                    if (e.getValue().equals("yes")) {
                        confirmCompetence(competenceOptional.get());
                    } else if (e.getValue().equals("no")) {
                        deleteCompetence(competenceOptional.get());
                    }
                } else throw new IllegalStateException("Student competence with studentId " +
                        studentId + " and competenceId " + competenceId + " not found");

            }
        }
    }

    @Override
    public void confirmCompetence(StudentCompetence studentCompetence) {
        studentCompetence.setConfirmed(true);
        competenceRepository.save(studentCompetence);
    }


    /**
     * This method is used to not confirm (because entries with false "confirmed"
     * boolean are considered unread)
     */
    @Override
    public void deleteCompetence(StudentCompetence studentCompetence) {
        competenceRepository.delete(studentCompetence);
    }
}
