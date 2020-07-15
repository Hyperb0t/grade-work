package ru.itis.jaboderzhateli.gradework.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("resume")
public class ResumeController {

    @GetMapping("/create")
    private String getResumeCreateForm(@AuthenticationPrincipal UserDetailsImpl userDetails, ModelMap map) {
        if(userDetails != null) {
            map.put("me", userDetails.getUser());
            List<String> competences = new ArrayList<>();
            competences.add("Программная инженерия");
            competences.add("Физика");
            competences.add("Биология");
            competences.add("Математика");
            map.put("competences", competences);
            return "main/resume_create";
        }
        return "main/landing";
    }
}
