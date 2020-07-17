package ru.itis.jaboderzhateli.gradework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;
import ru.itis.jaboderzhateli.gradework.services.interfaces.CompetenceService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ResumeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private CompetenceService competenceService;

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/create")
    public String getResumeCreateForm(@AuthenticationPrincipal UserDetailsImpl userDetails, ModelMap map) {
        map.put("competences", competenceService.getAllCompetences());
        return "main/resume_create";
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/create")
    public String createResume(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam Map<String, String> params, ModelMap map, HttpServletRequest request) {
        resumeService.createResume(userDetails.getUser(), params, request.getLocale());
        return "redirect:/user";
    }
}