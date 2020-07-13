package ru.itis.jaboderzhateli.gradework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/signIn")
    public String getSignInForm() {
        return "auth/sign_in";
    }

    @PostMapping("/signIn")
    public String signIn(@RequestParam Map<String, String> params) {
        System.out.println("PARAMS: " + params);
        return "redirect:/";
    }

    @GetMapping("/signUp/student")
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

    @PostMapping("/signUp/student")
    public String signUpStudent(@RequestParam Map<String, String> params) {
        System.out.println("PARAMS: " + params);
        return "redirect:/";
    }

    @GetMapping("/signUp/teacher")
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

    @PostMapping("/signUp/teacher")
    public String signUpTeacher(@RequestParam Map<String, String> params) {
        System.out.println("PARAMS: " + params);
        return "redirect:/";
    }

    @GetMapping("/signUp")
    public String getEmployerSignUpForm() {
        return "auth/sign_up_employer";
    }

    @PostMapping("/signUp")
    public String signUpEmployer(@RequestParam Map<String, String> params) {
        System.out.println("PARAMS: " + params);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getLanding() {
        return "main/landing";
    }
}