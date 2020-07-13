package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.jaboderzhateli.gradework.dto.forms.SignUpEmployerForm;
import ru.itis.jaboderzhateli.gradework.services.interfaces.SignUpService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/signUp")
public class SignUpController {

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

        return "";
    }

    @PostMapping("/student/file")
    public String signUpStudentFile(@RequestParam("file") MultipartFile multipartFile, BindingResult bindingResult) {

        return "";
    }

}
