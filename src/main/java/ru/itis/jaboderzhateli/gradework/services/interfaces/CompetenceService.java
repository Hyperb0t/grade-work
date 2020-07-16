package ru.itis.jaboderzhateli.gradework.services.interfaces;

import ru.itis.jaboderzhateli.gradework.models.Competence;

import java.util.List;

public interface CompetenceService {

    List<Competence> getAllCompetences();

    Competence getCompetence(String competenceName);
}
