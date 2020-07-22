package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.*;
import ru.itis.jaboderzhateli.gradework.repositories.CompetenceRepository;
import ru.itis.jaboderzhateli.gradework.repositories.ProjectRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentCompetenceRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ResumeService;

import java.util.*;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final MessageSource messageSource;
    private final StudentCompetenceRepository studentCompetenceRepository;
    private final CompetenceRepository competenceRepository;
    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    @Override
    public void createResume(User user, Map<String, String> params, Locale locale) {
        if(!user.getRole().equals(Role.STUDENT)) {
            throw new IllegalStateException("Resume creation available only for students!");
        }
        Optional<Student> studentOptional = studentRepository.findById(user.getId());
        if(studentOptional.isEmpty()) {
            throw new IllegalStateException("Student account doesn't exist!");
        }
        Student student = studentOptional.get();
        Set<String> competences = new HashSet<>();
        Map<String, String> projects = new HashMap<>();
        boolean studentChanged = false;
        for(Map.Entry<String, String> param : params.entrySet()) {
            switch (param.getKey()) {
                case "phone":
                    if(!param.getValue().trim().equals("") && param.getValue().length() < 14) {
                        student.setPhone(param.getValue());
                        studentChanged = true;
                    } else {
                        student.setPhone(null);
                    }
                    break;
                case "email":
                    if(param.getValue().matches(".+@.+\\..+")) {
                        student.setEmail(param.getValue());
                        studentChanged = true;
                    } else {
                        student.setEmail(null);
                    }
                    break;
                case "bio":
                    if(!param.getValue().trim().equals("")) {
                        student.setBio(param.getValue().replaceAll("\\r\\n", "<br>"));
                        studentChanged = true;
                    } else {
                        student.setBio(null);
                    }
                    break;
                default:
                    if(param.getKey().startsWith("competence-") && !param.getValue().equals(messageSource.getMessage("sign.competence.placeholder", null, locale))) {
                        competences.add(param.getValue());
                    } else if(param.getKey().startsWith("name-project-") && !param.getValue().trim().equals("")) {
                        projects.put(param.getKey(), param.getValue());
                    } else if(param.getKey().startsWith("link-project-")) {
                        if(param.getValue().trim().equals("")) {
                            projects.remove("name-project-" + param.getKey().substring("link-project-".length()));
                        } else {
                            String key = projects.remove("name-project-" + param.getKey().substring("link-project-".length()));
                            projects.put(key, param.getValue());
                        }
                    }
                    break;
            }
        }
        studentRepository.save(student);
        studentCompetenceRepository.deleteByStudentId(student.getId());
        if(competences.size() > 0) {
            competences.forEach(name -> {
                Optional<Competence> competenceOptional = competenceRepository.findByName(name);
                competenceOptional.ifPresent(competence -> {
                    if(studentCompetenceRepository.findByStudentIdAndCompetenceId(student.getId(), competence.getId()).isEmpty()) {
                        studentCompetenceRepository.save(new StudentCompetence(student, competence, false));
                    }
                });
            });
        }
        projectRepository.deleteByStudent_Id(student.getId());
        if(projects.size() > 0) {
            projects.forEach((key, value) -> {
                projectRepository.save(Project.builder().name(key).link(value).student(student).build());
            });
        }
    }
}