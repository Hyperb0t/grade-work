package ru.itis.jaboderzhateli.gradework.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.jaboderzhateli.gradework.models.Competence;
import ru.itis.jaboderzhateli.gradework.repositories.CompetenceRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.CompetenceService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CompetenceServiceTest {

    @Autowired
    private CompetenceService competenceService;
    @Mock
    private CompetenceRepository competenceRepository;

    @Test
    public void illegalArgumentWhenNameWrong() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            competenceService.getCompetence("sdfgs");
        });
    }

    @Test
    public void whenValid_found() {

        Competence competenceInRep = Competence.builder().name("Java").build();
        Mockito.when(competenceRepository.findByName(competenceInRep.getName())).thenReturn(Optional.of(competenceInRep));

        var name = "Java";
        var competence = competenceService.getCompetence(name);

        assertThat(competence.getName().equals(name));
    }

}
