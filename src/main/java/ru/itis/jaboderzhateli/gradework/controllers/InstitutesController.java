package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.jaboderzhateli.gradework.repositories.InstituteRepository;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;

@Controller
@AllArgsConstructor
public class InstitutesController {

    private final InstituteRepository instituteRepository;

    @PreAuthorize("permitAll()")
    @GetMapping("/institutes")
    public String getInstitutesPage(@AuthenticationPrincipal UserDetailsImpl userDetails, ModelMap map) {
        if(userDetails != null) {
            map.put("me", userDetails.getUser());
        }
        map.put("institutes", instituteRepository.findAll());
        return "main/institutes";
    }
}
