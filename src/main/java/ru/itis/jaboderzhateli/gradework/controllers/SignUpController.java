package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpEmployerForm;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpStudentForm;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpTeacherForm;
import ru.itis.jaboderzhateli.gradework.models.Competence;
import ru.itis.jaboderzhateli.gradework.models.Faculty;
import ru.itis.jaboderzhateli.gradework.models.Institute;
import ru.itis.jaboderzhateli.gradework.services.interfaces.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;
    private final InstituteService instituteService;
    private final FacultieService facultieService;
    private final CompetenceService competenceService;
    private final DynamicArgumentsParser parser;

    @GetMapping("/teacher")
    public String getTeacherSignUpForm(ModelMap map) {

        var institutes = instituteService.getAllInstitutes()
                .stream()
                .map(Institute::getName)
                .collect(Collectors.toList());
        var competences = competenceService.getAllCompetences()
                .stream()
                .map(Competence::getName)
                .collect(Collectors.toList());

        map.put("institutes", institutes);
        map.put("competences", competences);
        return "auth/sign_up_teacher";
    }

    @GetMapping
    public String getEmployerSignUpForm() {
        return "auth/sign_up_employer";
    }

    @GetMapping("/student")
    public String getStudentSignUpForm(ModelMap map) {

        var institutes = instituteService.getAllInstitutes()
                .stream()
                .map(Institute::getName)
                .collect(Collectors.toList());
        var faculties = facultieService.getAllFaculties()
                .stream()
                .map(Faculty::getName)
                .collect(Collectors.toList());

        map.put("institutes", institutes);
        map.put("faculties", faculties);
        return "auth/sign_up_student";
    }

    @PostMapping("/employer")
    public String signUpEmployer(@Valid SignUpEmployerForm form, BindingResult bindingResult, @RequestParam Map<String, String> allParams) {
//        if (bindingResult.hasErrors()) {
//            return "auth/sign_up_employer";
//        }
        var links = parser.parse(allParams, "link-[0-9]*");
        form.setLink(links);

        try {
            signUpService.signUp(form);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        System.out.println("Successfully saved employer");
        return "redirect:/signIn";
    }

    @PostMapping("/student")
    public String signUpStudent(@Valid SignUpStudentForm form, BindingResult bindingResult) {
        try {
            signUpService.signUp(form);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        System.out.println("Successfully saved student");
        return "redirect:/signIn";
    }

    @PostMapping("/teacher")
    public String signUpTeacher(@Valid SignUpTeacherForm form, BindingResult bindingResult, @RequestParam Map<String, String> allParams) {

        var competences = parser.parse(allParams, "competence-[0-9]*");
        form.setCompetence(competences);

        try {
            signUpService.signUp(form);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        System.out.println("Successfully saved teacher");
        return "redirect:/signIn";
    }

    @PostMapping("/teacher/file")
    public String signUpTeacherFile(@RequestParam("file") MultipartFile multipartFile) {
        signUpService.signUpTeachers(multipartFile);
        return "forward:/";
    }

    @PostMapping("/student/file")
    public String signUpStudentFile(@RequestParam("file") MultipartFile multipartFile) {
        signUpService.signUpStudents(multipartFile);
        return "forward:/";
    }

}
