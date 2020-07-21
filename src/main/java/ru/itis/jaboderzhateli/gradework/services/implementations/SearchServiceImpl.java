package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.*;
import ru.itis.jaboderzhateli.gradework.repositories.*;
import ru.itis.jaboderzhateli.gradework.services.interfaces.SearchService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final StudentRepository studentRepository;
    private final CompetenceRepository competenceRepository;
    private final StudentCompetenceRepository studentCompetenceRepository;

    @Override
    public List<Student> findFromRequest(Map<String, String> params) {
        if (params.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> competenceId = new LinkedList<>();
        Long instituteId = null;
        Long facultyId = null;

        for (Map.Entry<String, String> e : params.entrySet()) {
            if (e.getKey().equals("c")) {
                try {
                    competenceId.add(Long.parseLong(e.getValue()));
                } catch (NumberFormatException ignored) {
                }
            } else if (e.getKey().equals("i")) {
                if (!e.getValue().equals("-1")) {
                    try {
                        instituteId = Long.parseLong(e.getValue());
                    } catch (NumberFormatException ignored) {
                    }
                }
            } else if (e.getKey().equals("f")) {
                if (!e.getValue().equals("-1")) {
                    try {
                        facultyId = Long.parseLong(e.getValue());
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        }

        //the code is long, but asks DB as little as it can
        //and also it doesn't fuckin work
        if (competenceId.isEmpty()) {
            if (instituteId != null) {
                if (facultyId != null)
                    return studentRepository.findAllByInstituteIdAndFacultyId(instituteId, facultyId);
                else
                    return studentRepository.findAllByInstituteId(instituteId);
            } else {
                if (facultyId != null)
                    return studentRepository.findAllByFacultyId(facultyId);
                else
                    return Collections.emptyList();
            }
        } else {
            List<Competence> competences = competenceRepository.findAllByIdIn(competenceId);
            log.info("found compet. by ids: " + competences.toString());
            List<StudentCompetence> studentCompetences = studentCompetenceRepository.findAllByConfirmedIsAndCompetenceIn(true, competences);
            log.info(studentCompetences.toString());
            if (instituteId != null) {
                if (facultyId != null)
                    return studentRepository.findAllByInstituteIdAndFacultyId(instituteId, facultyId).stream().
                            filter(s -> studentHasAllCompetences(s, competences))
                            .collect(Collectors.toList());
                else
                    return studentRepository.findAllByInstituteId(instituteId).stream().
                            filter(s -> studentHasAllCompetences(s, competences))
                            .collect(Collectors.toList());
            } else {
                if (facultyId != null)
                    return studentRepository.findAllByFacultyId(facultyId).stream().
                            filter(s -> studentHasAllCompetences(s, competences))
                            .collect(Collectors.toList());
                else
                    return studentRepository.findAll().stream().
                            filter(s -> studentHasAllCompetences(s, competences))
                            .collect(Collectors.toList());
                //return studentRepository.findAllByCompetencesIn(studentCompetences);
            }
        }
    }

    private Boolean studentHasAllCompetences(Student st, List<Competence> competences) {
        for (Competence comp : competences) {
            boolean hasThisComp = false;
            for (StudentCompetence stComp : st.getCompetences()) {
                if (stComp.getConfirmed() && stComp.getCompetence().getId().equals(comp.getId())) {
                    hasThisComp = true;
                    break;
                }
            }
            if (!hasThisComp) {
                return false;
            }
        }
        return true;
    }

    private Boolean studentHasMostCompetences(Student st, List<Competence> competences, Integer missingAllowed) {
        for (Competence comp : competences) {
            boolean hasThisComp = false;
            int missing = 0;
            for (StudentCompetence stComp : st.getCompetences()) {
                if (stComp.getConfirmed() && stComp.getCompetence().getId().equals(comp.getId())) {
                    hasThisComp = true;
                    break;
                }
            }
            if (!hasThisComp) {
                missing++;
                if (missing > missingAllowed) {
                    return false;
                }
            }
        }
        return true;
    }

}
