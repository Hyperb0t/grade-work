package ru.itis.jaboderzhateli.gradework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.JobApplicationRepository;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ApplicationService;

import java.util.Map;
import java.util.Optional;

@Controller
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private JobApplicationRepository applicationRepository;
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/employers")
    public String employersPage(Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        return "main/employers_list";
    }

    @GetMapping("/applications")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String applicationsPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long employerId = userDetails.getId();
        Optional<Employer> employerOptional = employerRepository.findById(employerId);
        if(employerOptional.isPresent()) {
            model.addAttribute("unreadApplications",
                    applicationService.getApplications(employerOptional.get(), false));
            model.addAttribute("readApplications",
                    applicationService.getApplications(employerOptional.get(), false));
        }
        return "main/employer_applications";
    }

    @PostMapping("/applications")
    public String markRead(@RequestParam Map<String, String> params) {
        for(Map.Entry<String, String> e : params.entrySet()) {
            try {
                Long applicationId = Long.parseLong(e.getKey());
                applicationService.setRead(applicationRepository.findById(applicationId).get(), true);
            }catch (Exception ignored) {}
        }
        return "redirect:/applications";
    }

    @GetMapping("/apply/{user-id}")
    public String createApplication (){
        return "redirect:/employers";
    }
}
