package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.jaboderzhateli.gradework.models.*;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.repositories.TeacherRepository;
import ru.itis.jaboderzhateli.gradework.repositories.UserRepository;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ChatService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.EmployerService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.StudentService;
import ru.itis.jaboderzhateli.gradework.services.interfaces.TeacherService;

import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserPageController {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final EmployerRepository employerRepository;
    private final UserRepository userRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final EmployerService employerService;
    private final ChatService chatService;

    @PreAuthorize("permitAll()")
    @GetMapping("/user")
    public String getPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            return "redirect:/user/" + userDetails.getId();
        } else {
            return "redirect:/signIn";
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/user/{user-id}")
    public String getPage(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("user-id") String userId, ModelMap map) {
        if (userDetails != null) {
            map.put("me", userDetails.getUser());
        }
        long id = Long.parseLong(userId);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return "main/landing";
        }
        User user = userOptional.get();
        boolean me = false;
        if (userDetails != null && user.getId().equals(userDetails.getId())) {
            me = true;
        }
        switch (user.getRole()) {
            case STUDENT:
                Optional<Student> studentOptional = studentRepository.findById(id);
                if (studentOptional.isPresent()) {
                    boolean hasUnconfirmed = false;
                    for (StudentCompetence competence : studentOptional.get().getCompetences()) {
                        if (!competence.getConfirmed()) {
                            hasUnconfirmed = true;
                            break;
                        }
                    }
                    map.put("unconfirmed", hasUnconfirmed);
                    map.put("student", studentOptional.get());
                    return "main/student_page";
                }
                break;
            case EMPLOYER:
                Optional<Employer> employerOptional = employerRepository.findById(id);
                if (employerOptional.isPresent()) {
                    map.put("employer", employerOptional.get());
                    return "main/employer_page";
                }
                break;
            case TEACHER:
                Optional<Teacher> teacherOptional = teacherRepository.findById(id);
                if (teacherOptional.isPresent()) {
                    map.put("teacher", teacherOptional.get());
                    return "main/teacher_page";
                }
                break;
            case ADMINISTRATION:
                map.put("administration", user);
                return "main/administration_page";
        }
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/user/{user-id}/edit")
    public String editUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("user-id") Long userId,
                           ModelMap map, Map<String, String> params) {

        if (userDetails != null) {
            map.put("me", userDetails.getUser());
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return "main/landing";
        }

        User user = userOptional.get();
        var currentUser = userDetails.getUser();
        String page = resolveForUser(userId, map, user, currentUser, params);

        if (page != null) return page;

        return "redirect:/";
    }

    private String resolveForUser(@PathVariable("user-id") Long userId, ModelMap map, User user, User currentUser, Map<String, String> params) {
        switch (user.getRole()) {
            case STUDENT:
                if (userId.equals(currentUser.getId()) | currentUser.getRole().equals(Role.ADMINISTRATION))
                    map.put("student", studentService.edit(user, params));
                return "main/student_page";
            case EMPLOYER:
                if (userId.equals(currentUser.getId()) | currentUser.getRole().equals(Role.ADMINISTRATION))
                    map.put("employer", employerService.edit(user, params));
            case TEACHER:
                if (userId.equals(currentUser.getId()) | currentUser.getRole().equals(Role.ADMINISTRATION))
                    map.put("teacher", teacherService.edit(user, params));
                return "main/student_page";
            case ADMINISTRATION:
                return "main/administration_page";
        }
        return null;
    }
}