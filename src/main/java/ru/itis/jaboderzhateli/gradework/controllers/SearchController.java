package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.jaboderzhateli.gradework.repositories.CompetenceRepository;
import ru.itis.jaboderzhateli.gradework.repositories.FacultyRepository;
import ru.itis.jaboderzhateli.gradework.repositories.InstituteRepository;
import ru.itis.jaboderzhateli.gradework.services.interfaces.SearchService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class SearchController {

    @Autowired
    private InstituteRepository instituteRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private CompetenceRepository competenceRepository;
    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String searchPage(Model model, @RequestParam Map<String, String> params) {
        Long selectedInst = -1l;
        if(params.containsKey("i"))
            selectedInst = Long.parseLong(params.get("i"));
        Long selectedFac = -1l;
        if(params.containsKey("f"))
            selectedFac = Long.parseLong(params.get("f"));
        List<Long> selectedComp = new LinkedList<>();
        params.entrySet().stream().filter(e -> e.getKey().equals("c"))
                .forEach(e ->selectedComp.add(Long.parseLong(e.getValue())));
        log.info("selected comps.: " + selectedComp.toString());
        model.addAttribute("selectedInst",selectedInst);
        model.addAttribute("selectedFac",selectedFac);
        model.addAttribute("selectedComp",selectedComp);
        model.addAttribute("competences", competenceRepository.findAll());
        model.addAttribute("institutes", instituteRepository.findAll());
        model.addAttribute("faculties", facultyRepository.findAll());
        model.addAttribute("students", searchService.findFromRequest(params));
        return "main/search";
    }

}
