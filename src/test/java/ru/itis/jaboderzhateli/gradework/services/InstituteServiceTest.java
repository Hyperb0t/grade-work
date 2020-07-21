package ru.itis.jaboderzhateli.gradework.services;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.jaboderzhateli.gradework.models.Institute;
import ru.itis.jaboderzhateli.gradework.repositories.InstituteRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.InstituteService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class InstituteServiceTest {

    @Autowired
    private InstituteService instituteService;
    @Mock
    private InstituteRepository instituteRepository;

    @Before
    public void setup() {
        Institute institute = Institute.builder().name("Высшая школа информационных технологий и интеллектуальных систем").build();
        Mockito.when(instituteRepository.findByName(institute.getName())).thenReturn(Optional.of(institute));
    }

    @Test
    public void illegalArgumentWhenNameWrong() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            instituteService.getInstitute("12");
        });
    }

    @Test
    public void whenValid_found() {
        var name = "Высшая школа информационных технологий и интеллектуальных систем";
        var institute = instituteService.getInstitute(name);

        assertThat(institute.getName().equals(name));
    }

}
