package ru.itis.jaboderzhateli.gradework.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.jaboderzhateli.gradework.models.Institute;
import ru.itis.jaboderzhateli.gradework.repositories.InstituteRepository;
import ru.itis.jaboderzhateli.gradework.services.implementations.InstituteServiceImpl;
import ru.itis.jaboderzhateli.gradework.services.interfaces.InstituteService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InstituteServiceTest {

    @Autowired
    private InstituteService instituteService;
    @Mock
    private InstituteRepository instituteRepository;

    @Before
    public void setup(){
        Institute institute = Institute.builder().name("ВШ ИТИС").build();
        Mockito.when(instituteRepository.findByName(institute.getName())).thenReturn(Optional.of(institute));
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentWhenNameWrong(){
        instituteService.getInstitute("12");
        System.out.println("asd");
    }

    @Test
    public void whenValid_found(){
        var name = "ВШ ИТИС";
        var institute = instituteService.getInstitute(name);

        assertThat(institute.getName().equals(name));
    }

}
