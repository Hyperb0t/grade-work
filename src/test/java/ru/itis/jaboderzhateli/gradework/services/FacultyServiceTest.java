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
import ru.itis.jaboderzhateli.gradework.models.Institute;
import ru.itis.jaboderzhateli.gradework.repositories.FacultyRepository;
import ru.itis.jaboderzhateli.gradework.repositories.InstituteRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.FacultyService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.InstituteService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FacultyServiceTest {

    @Autowired
    private FacultyService facultyService;
    @Mock
    private FacultyRepository facultyRepository;

    @Before
    public void setup(){
        Faculty faculty = Faculty.builder().name("Программная инженерия").build();
        Mockito.when(facultyRepository.findByName(faculty.getName())).thenReturn(Optional.of(faculty));
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentWhenNameWrong(){
        facultyService.getFaculty("sdfdsf");
    }

    @Test
    public void whenValid_found(){
        var name = "Программная инженерия";
        var institute = facultyService.getFaculty(name);

        assertThat(institute.getName().equals(name));
    }

}
