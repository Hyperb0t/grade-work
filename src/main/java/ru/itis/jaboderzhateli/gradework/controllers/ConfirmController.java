package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.jaboderzhateli.gradework.models.User;
import ru.itis.jaboderzhateli.gradework.repositories.TeacherRepository;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;
import ru.itis.jaboderzhateli.gradework.services.interfaces.CompetenceConfirmService;

import java.util.Map;

/**
 * @author Vitaly
 */

@Controller
@Slf4j
@AllArgsConstructor
public class ConfirmController {

    private final CompetenceConfirmService confirmService;
    private final TeacherRepository teacherRepository;

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/confirm/competences")
    public String getConfirmationsPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) { ;
        model.addAttribute("teacher", teacherRepository.findById(userDetails.getId()).get());
        model.addAttribute("competences", confirmService.getCompetencesToConfirm(userDetails.getId()));
        return "main/teacher_confirm";
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/confirm/competences")
    public String receiveConfirmed(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam Map<String, String> params) {
        log.info("received POST from /confirm/competences");
        params.entrySet().stream().forEach(e -> log.info(e.toString()));
        confirmService.confirmFromRequest(params);
        return "redirect:/user";
    }
}
