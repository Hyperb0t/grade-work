package ru.itis.jaboderzhateli.gradework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.jaboderzhateli.gradework.models.Employer;
import ru.itis.jaboderzhateli.gradework.models.Student;
import ru.itis.jaboderzhateli.gradework.models.Teacher;
import ru.itis.jaboderzhateli.gradework.models.User;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.repositories.TeacherRepository;
import ru.itis.jaboderzhateli.gradework.repositories.UserRepository;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;

import java.util.Optional;

@Controller
public class UserPageController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("permitAll()")
    @GetMapping("/user")
    public String getPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails != null) {
            return "redirect:/user/" + userDetails.getId();
        } else {
            return "redirect:/signIn";
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/user/{user-id}")
    public String getPage(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("user-id") String userId, ModelMap map) {
        if(userDetails != null) {
            map.put("me", userDetails.getUser());
        }
        long id = Long.parseLong(userId);
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) {
            return "main/landing";
        }
        User user = userOptional.get();
        switch (user.getRole()) {
            case STUDENT:
                Optional<Student> studentOptional = studentRepository.findById(id);
                if(studentOptional.isPresent()) {
                    map.put("student", studentOptional.get());
                    return "main/student_page";
                }
                break;
            case EMPLOYER:
                Optional<Employer> employerOptional = employerRepository.findById(id);
                if(employerOptional.isPresent()) {
                    map.put("employer", employerOptional.get());
                    return "main/employer_page";
                }
                break;
            case TEACHER:
                Optional<Teacher> teacherOptional =  teacherRepository.findById(id);
                if(teacherOptional.isPresent()) {
                    map.put("teacher", teacherOptional.get());
                    return "main/teacher_page";
                }
                break;
            case ADMINISTRATION:
                map.put("administration", user);
                return "main/administration_page";
        }
        return "main/landing";
    }
}