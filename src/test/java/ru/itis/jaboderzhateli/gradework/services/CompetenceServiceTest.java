package ru.itis.jaboderzhateli.gradework.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.jaboderzhateli.gradework.models.Competence;
import ru.itis.jaboderzhateli.gradework.models.Faculty;
import ru.itis.jaboderzhateli.gradework.repositories.CompetenceRepository;
import ru.itis.jaboderzhateli.gradework.repositories.FacultyRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.CompetenceService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.FacultyService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CompetenceServiceTest {

    @Autowired
    private CompetenceService competenceService;
    @Mock
    private CompetenceRepository competenceRepository;

    @Before
    public void setup(){
        Competence competence = Competence.builder().name("Java").build();
        Mockito.when(competenceRepository.findByName(competence.getName())).thenReturn(Optional.of(competence));
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentWhenNameWrong(){
        competenceService.getCompetence("sdfgs");
    }

    @Test
    public void whenValid_found(){
        var name = "Java";
        var competence = competenceService.getCompetence(name);

        assertThat(competence.getName().equals(name));
    }

}
