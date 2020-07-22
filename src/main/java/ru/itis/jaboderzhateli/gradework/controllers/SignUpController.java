package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import ru.itis.jaboderzhateli.gradework.utils.excelLoader.ExcelConverter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;
    private final InstituteService instituteService;
    private final FacultyService facultyService;
    private final CompetenceService competenceService;
    private final DynamicArgumentsParser parser;
    private final ExcelConverter handler;

    @PreAuthorize("isAnonymous()")
    @GetMapping
    public String getEmployerSignUpForm() {
        return "auth/sign_up_employer";
    }

    @PreAuthorize("hasRole('ADMINISTRATION')")
    @GetMapping("/student/file")
    public String getStudentSignUpFileForm() {
        return "auth/sign_up_student_file";
    }

    @PreAuthorize("hasRole('ADMINISTRATION')")
    @GetMapping("/teacher/file")
    public String getTeacherSignUpFileForm() {
        return "auth/sign_up_teacher_file";
    }

    @PreAuthorize("hasRole('ADMINISTRATION')")
    @GetMapping("/student")
    public String getStudentSignUpForm(ModelMap map) {

        var institutes = instituteService.getAllInstitutes()
                .stream()
                .map(Institute::getName)
                .collect(Collectors.toList());
        var faculties = facultyService.getAllFaculties()
                .stream()
                .map(Faculty::getName)
                .collect(Collectors.toList());

        map.put("institutes", institutes);
        map.put("faculties", faculties);
        return "auth/sign_up_student";
    }

    @PreAuthorize("hasRole('ADMINISTRATION')")
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

    @PreAuthorize("isAnonymous()")
    @PostMapping
    public String signUpEmployer(@Valid SignUpEmployerForm form, BindingResult bindingResult, @RequestParam Map<String, String> allParams) {

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

    @PreAuthorize("hasRole('ADMINISTRATION')")
    @PostMapping("/student")
    public String signUpStudent(@Valid SignUpStudentForm form, BindingResult bindingResult) {
        try {
            signUpService.signUp(form);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        System.out.println("Successfully saved student");
        return "redirect:/user";
    }

    @PreAuthorize("hasRole('ADMINISTRATION')")
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
        return "redirect:/user";
    }

    @PreAuthorize("hasRole('ADMINISTRATION')")
    @PostMapping("/teacher/file")
    public void signUpTeacherFile(@RequestParam("file") MultipartFile multipartFile, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=\"Teachers.xls\"");
        handler.downloadTeachers(signUpService.signUpTeachers(multipartFile), response);
    }

    @PreAuthorize("hasRole('ADMINISTRATION')")
    @PostMapping("/student/file")
    public void signUpStudentFile(@RequestParam("file") MultipartFile multipartFile, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=\"Students.xls\"");
        handler.downloadStudents(signUpService.signUpStudents(multipartFile), response);
    }
}
