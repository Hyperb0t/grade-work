package ru.itis.jaboderzhateli.gradework.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.jaboderzhateli.gradework.models.Faculty;
import ru.itis.jaboderzhateli.gradework.models.StudentCompetence;
import ru.itis.jaboderzhateli.gradework.repositories.StudentCompetenceRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.CompetenceConfirmService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CompetenceConfirmServiceTest {

    @Mock
    private StudentCompetenceRepository competenceRepository;
    @Autowired
    private CompetenceConfirmService competenceConfirmService;

    @Before
    public void setup(){
        Faculty faculty = Faculty.builder().name("Программная инженерия").build();
        Mockito.when(competenceRepository.findByStudentIdAndCompetenceId(1L, 1L))
                .thenReturn(Optional.of(StudentCompetence.builder().build()));
        Mockito.when(competenceRepository.save(StudentCompetence.builder().build()))
                .thenReturn(StudentCompetence.builder().build());
    }

    //Doesn't exist in repo
    @Test(expected = IllegalStateException.class)
    public void exceptionWhenUserDoesntExist(){
        Map<String, String> map = new HashMap<>();
        map.put("1_1","yes");
        competenceConfirmService.confirmFromRequest(map);
    }

}
