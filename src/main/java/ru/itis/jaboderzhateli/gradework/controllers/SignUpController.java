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
import ru.itis.jaboderzhateli.gradework.services.interfaces.SignUpService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/signUp")
public class SignUpController {

    @GetMapping("/teacher")
    public String getTeacherSignUpForm(ModelMap map) {
        List<String> institutes = new ArrayList<>();
        institutes.add("ИТИС");
        institutes.add("ИВМИИТ");
        institutes.add("Юрфак");
        institutes.add("Эконом");
        List<String> competences = new ArrayList<>();
        competences.add("Программная инженерия");
        competences.add("Физика");
        competences.add("Биология");
        competences.add("Математика");
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
        List<String> institutes = new ArrayList<>();
        institutes.add("ИТИС");
        institutes.add("ИВМИИТ");
        institutes.add("Юрфак");
        institutes.add("Эконом");
        List<String> faculties = new ArrayList<>();
        faculties.add("Программная инженерия");
        faculties.add("Физика");
        faculties.add("Биология");
        faculties.add("Математика");
        map.put("institutes", institutes);
        map.put("faculties", faculties);
        return "auth/sign_up_student";
    }

    private final SignUpService signUpService;

    @PostMapping("/employer")
    public String signUpEmployer(@Valid SignUpEmployerForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "";
        }

        try {
            signUpService.signUp(form);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        System.out.println("forwarding to /sign_in");
        return "forward:/sign_in";
    }

    @PostMapping("/teacher/file")
    public String signUpTeacherFile(@RequestParam("file") MultipartFile multipartFile, BindingResult bindingResult) {
        signUpService.signUpTeacher(multipartFile);
        return "";
    }

    @PostMapping("/student/file")
    public String signUpStudentFile(@RequestParam("file") MultipartFile multipartFile, BindingResult bindingResult) {
        signUpService.signUpStudent(multipartFile);
        return "";
    }

}
