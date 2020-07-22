package ru.itis.jaboderzhateli.gradework.services;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.jaboderzhateli.gradework.models.Faculty;
import ru.itis.jaboderzhateli.gradework.repositories.FacultyRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.FacultyService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FacultyServiceTest {

    @Autowired
    private FacultyService facultyService;
    @Mock
    private FacultyRepository facultyRepository;

    @Before
    public void setup() {
        Faculty faculty = Faculty.builder().name("Программная инженерия").build();
        Mockito.when(facultyRepository.findByName(faculty.getName())).thenReturn(Optional.of(faculty));
    }

    @Test
    public void illegalArgumentWhenNameWrong() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            facultyService.getFaculty("sdfdsf");
        });

    }

    @Test
    public void whenValid_found() {
        var name = "Программная инженерия";
        var institute = facultyService.getFaculty(name);

        assertThat(institute.getName().equals(name));
    }

}
