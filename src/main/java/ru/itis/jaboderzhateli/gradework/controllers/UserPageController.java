package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.jaboderzhateli.gradework.models.*;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.repositories.TeacherRepository;
import ru.itis.jaboderzhateli.gradework.repositories.UserRepository;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;
import ru.itis.jaboderzhateli.gradework.services.interfaces.ChatService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserPageController {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final EmployerRepository employerRepository;
    private final UserRepository userRepository;

    @Autowired
    private ChatService chatService;

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
        if(userDetails != null && !user.getId().equals(userDetails.getId())) {
            List<Channel> suitables = chatService.checkIfChannelExistsForUsers(userDetails.getId(), id);
            if(!suitables.isEmpty()) {
                map.put("channelId", suitables.get(0).getId());
            }
        }
        switch (user.getRole()) {
            case STUDENT:
                Optional<Student> studentOptional = studentRepository.findById(id);
                if(studentOptional.isPresent()) {
                    boolean hasUnconfirmed = false;
                    for(StudentCompetence competence : studentOptional.get().getCompetences()) {
                        if(!competence.getConfirmed()) {
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
        return "redirect:/";
    }
}