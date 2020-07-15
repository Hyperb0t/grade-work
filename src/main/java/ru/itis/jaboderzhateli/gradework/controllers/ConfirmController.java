package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ConfirmController {

    @Autowired
    private CompetenceConfirmService confirmService;
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/confirmations")
    public String getConfirmationsPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) { ;
        User user = userDetails.getUser();
        Long userId = userDetails.getUser().getId();
        model.addAttribute("teacher", teacherRepository.findById(userId).get());
        model.addAttribute("competences", confirmService.getCompetencesToConfirm(userId));
        return "main/confirmations";
    }

    @PostMapping("/confirmations")
    public String receiveConfirmed(@RequestParam Map<String, String> params,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("received POST from /confirmations");
        params.entrySet().stream().forEach(e -> log.info(e.toString()));
        confirmService.confirmFromRequest(params);
        return "redirect:/user";
    }
}
