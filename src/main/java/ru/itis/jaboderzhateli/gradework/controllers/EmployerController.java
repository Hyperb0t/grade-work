package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.JobApplicationRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ApplicationService;

import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class EmployerController {

    private final EmployerRepository employerRepository;
    private final JobApplicationRepository applicationRepository;
    private final ApplicationService applicationService;
    private final StudentRepository studentRepository;

    @GetMapping("/employers")
    public String employersPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        if(userDetails != null) {
            model.addAttribute("me", userDetails.getUser());
        }
        model.addAttribute("employers", employerRepository.findAll());
        return "main/employers";
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
                    applicationService.getApplications(employerOptional.get(), true));
        }
        return "main/employer_applications";
    }

    @GetMapping("/apply/{user-id}")
    @PreAuthorize("hasRole('STUDENT')")
    public String createApplication (@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("user-id") Long userId, ModelMap map){
        Optional<Employer> employerOptional = employerRepository.findById(userId);
        Optional<Student> studentOptional = studentRepository.findById(userDetails.getId());
        if(employerOptional.isPresent() && studentOptional.isPresent()) {
            applicationService.apply(studentOptional.get(), employerOptional.get());
        }
        return "redirect:/employers";
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
}
