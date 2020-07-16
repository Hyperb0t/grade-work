package ru.itis.jaboderzhateli.gradework.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.jaboderzhateli.gradework.models.Competence;
import ru.itis.jaboderzhateli.gradework.repositories.CompetenceRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.CompetenceService;

import java.util.List;

@Service
@AllArgsConstructor
public class CompetenceServiceImpl implements CompetenceService {

    private final CompetenceRepository competenceRepository;

    @Override
    public List<Competence> getAllCompetences() {
        return competenceRepository.findAll();
    }

    @Override
    public Competence getCompetence(String competenceName) {
        Competence competence;
        var competenceCandidate = competenceRepository.findByName(competenceName);
        competence = competenceCandidate.orElseThrow(() -> new IllegalArgumentException(
                "Competence with specified name " + competenceName + " does not exist"));
        return competence;
    }


}
