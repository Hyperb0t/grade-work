package ru.itis.jaboderzhateli.gradework.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.jaboderzhateli.gradework.models.Faculty;
import ru.itis.jaboderzhateli.gradework.models.StudentCompetence;
import ru.itis.jaboderzhateli.gradework.repositories.StudentCompetenceRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.CompetenceConfirmService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
public class CompetenceConfirmServiceTest {

    @Mock
    private StudentCompetenceRepository competenceRepository;
    @Autowired
    private CompetenceConfirmService competenceConfirmService;

    @Test
    public void exceptionWhenUserDoesntExist() {
        Mockito.when(competenceRepository.findByStudentIdAndCompetenceId(1L, 1L))
                .thenReturn(Optional.of(StudentCompetence.builder().build()));
        Mockito.when(competenceRepository.save(StudentCompetence.builder().build()))
                .thenReturn(StudentCompetence.builder().build());

        Assertions.assertThrows(IllegalStateException.class, () -> {
            Map<String, String> map = new HashMap<>();
            map.put("1_1", "yes");
            competenceConfirmService.confirmFromRequest(map);
        });
    }
}
