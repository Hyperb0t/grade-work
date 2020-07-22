package ru.itis.jaboderzhateli.gradework.services;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.jaboderzhateli.gradework.models.Competence;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.StudentCompetence;
import ru.itis.jaboderzhateli.gradework.repositories.CompetenceRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentCompetenceRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.SearchService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SearchServiceTest {

    @Autowired
    private SearchService searchService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentCompetenceRepository studentCompetenceRepository;
    @Autowired
    private CompetenceRepository competenceRepository;

    @Test
    public void nullPointerWhenParamsIsNull() {
        Assertions.assertThrows(NullPointerException.class,
                () -> searchService.findFromRequest(null));
    }

    @Test
    public void emptyWhenParamsIsEmpty() {
        Map<String, String> map = new HashMap<>();
        assertEquals(searchService.findFromRequest(map), Collections.emptyList());
    }

    @Test
    public void competenceFacultyEmptyAndInstituteIsPresent() {
        Map<String, String> map = new HashMap<>();
        map.put("i", "9");

        var list = studentRepository.findAllByInstituteId(Long.parseLong(map.get("i")));
        var resultList = searchService.findFromRequest(map);
        boolean flag = true;

        if (list.size() == resultList.size()) {
            for (int i = 0; i < list.size(); ++i) {
                if (!list.get(i).getLogin().equals(resultList.get(i).getLogin())) {
                    flag = false;
                    break;
                }
            }

        }
        assertTrue(flag);
    }

    @Test
    public void competenceInstituteEmptyAndFacultyIsPresent() {
        Map<String, String> map = new HashMap<>();
        map.put("f", "1");

        var list = studentRepository.findAllByFacultyId(Long.parseLong(map.get("f")));
        var resultList = searchService.findFromRequest(map);
        boolean flag = true;

        if (list.size() == resultList.size()) {
            for (int i = 0; i < list.size(); ++i) {
                if (!list.get(i).getLogin().equals(resultList.get(i).getLogin())) {
                    flag = false;
                    break;
                }
            }

        }
        assertTrue(flag);
    }

//    @Test
//    public void FacultyEmptyAndCompetenceInstituteIsPresent() {
//        Map<String, String> map = new HashMap<>();
//        map.put("i", "9");
//        map.put("c", "1");
////        var competences = competenceRepository.findAllByIdIn(List.of(Long.parseLong(map.get("c"))));
////        var tempList = studentRepository.findAll().stream().
////                filter(s -> studentHasAllCompetences(s, competences))
////                .collect(Collectors.toList());
//
//        var resList = searchService.findFromRequest(map);
//        boolean flag = true;
////
////        if (tempList.size() == resList.size()) {
////            for (int i = 0; i < tempList.size(); ++i) {
////                if (!tempList.get(i).getLogin().equals(resList.get(i).getLogin())) {
////                    flag = false;
////                    break;
////                }
////            }
////
////        }

//        Assertions.assertTrue(flag);
//    }


    private Boolean studentHasAllCompetences(Student st, List<Competence> competences) {
        for (Competence comp : competences) {
            boolean hasThisComp = false;
            for (StudentCompetence stComp : st.getCompetences()) {
                if (stComp.getConfirmed() && stComp.getCompetence().getId().equals(comp.getId())) {
                    hasThisComp = true;
                    break;
                }
            }
            if (!hasThisComp) {
                return false;
            }
        }
        return true;
    }
}
