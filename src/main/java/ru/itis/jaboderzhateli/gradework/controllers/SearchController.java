package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.jaboderzhateli.gradework.repositories.CompetenceRepository;
import ru.itis.jaboderzhateli.gradework.repositories.FacultyRepository;
import ru.itis.jaboderzhateli.gradework.repositories.InstituteRepository;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;
import ru.itis.jaboderzhateli.gradework.services.interfaces.SearchService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@AllArgsConstructor
public class SearchController {

    private final MessageSource messageSource;
    private final InstituteRepository instituteRepository;
    private final FacultyRepository facultyRepository;
    private final CompetenceRepository competenceRepository;
    private final SearchService searchService;

    @GetMapping("/search")
    public String searchPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model, @RequestParam Map<String, String> params) {
        if(userDetails != null) {
            model.addAttribute("me", userDetails.getUser());
        }
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
        model.addAttribute("selectedInst", selectedInst);
        model.addAttribute("selectedFac", selectedFac);
        model.addAttribute("selectedComp", selectedComp);
        model.addAttribute("competences", competenceRepository.findAll());
        model.addAttribute("institutes", instituteRepository.findAll());
        model.addAttribute("faculties", facultyRepository.findAll());
        model.addAttribute("students", searchService.findFromRequest(params));
        return "main/search";
    }

}
