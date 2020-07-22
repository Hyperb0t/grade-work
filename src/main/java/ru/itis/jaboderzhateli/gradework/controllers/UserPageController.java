package ru.itis.jaboderzhateli.gradework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.jaboderzhateli.gradework.models.*;
import ru.itis.jaboderzhateli.gradework.repositories.EmployerRepository;
import ru.itis.jaboderzhateli.gradework.repositories.StudentRepository;
import ru.itis.jaboderzhateli.gradework.repositories.TeacherRepository;
import ru.itis.jaboderzhateli.gradework.repositories.UserRepository;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;
import ru.itis.jaboderzhateli.gradework.services.interfaces.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final FacultyService facultyService;
    private final InstituteService instituteService;
    private final CompetenceService competenceService;

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
        if (userDetails != null && !user.getId().equals(userDetails.getId())) {
            List<Channel> suitables = chatService.checkIfChannelExistsForUsers(userDetails.getId(), id);
            if (!suitables.isEmpty()) {
                map.put("channelId", suitables.get(0).getId());
            }
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
    @GetMapping("/user/{user-id}/edit")
    public String getEditPage(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("user-id") Long userId, ModelMap map) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            map.put("me", userDetails.getUser());
            User user = userOptional.get();
            switch (user.getRole()) {
                case STUDENT:
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
                    map.put("user", studentRepository.findById(userId).get());
                    return "main/student_page_edit";
                case TEACHER:
                    var instits = instituteService.getAllInstitutes()
                            .stream()
                            .map(Institute::getName)
                            .collect(Collectors.toList());
                    var competences = competenceService.getAllCompetences()
                            .stream()
                            .map(Competence::getName)
                            .collect(Collectors.toList());
                    map.put("institutes", instits);
                    map.put("competences", competences);
                    map.put("user", teacherRepository.findById(userId).get());
                    return "main/teacher_page_edit";
                case EMPLOYER:
                    map.put("user", employerRepository.findById(userId).get());
                    return "main/employer_page_edit";
            }
        }
        return "redirect:/user";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/user/{user-id}/edit")
    public String editUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("user-id") Long userId,
                           ModelMap map, @RequestParam Map<String, String> params) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            resolveForUser(user, userDetails.getUser(), params);
            return "redirect:/user/" + userId;
        }
        return "redirect:/user";
    }

    private void resolveForUser(User user, User me, Map<String, String> params) {
        switch (user.getRole()) {
            case STUDENT:
                if(user.getId().equals(me.getId()) || me.getRole().equals(Role.ADMINISTRATION)) {
                    studentService.edit(user, params);
                }
                break;
            case EMPLOYER:
                if(user.getId().equals(me.getId()) || me.getRole().equals(Role.ADMINISTRATION)) {
                    employerService.edit(user, params);
                }
                break;
            case TEACHER:
                if(user.getId().equals(me.getId()) || me.getRole().equals(Role.ADMINISTRATION)) {
                    teacherService.edit(user, params);
                }
                break;
            case ADMINISTRATION:
                break;
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATION')")
    @GetMapping("/users")
    public String getUsersListPage(@AuthenticationPrincipal UserDetailsImpl userDetails, ModelMap map) {
        map.put("students", studentRepository.findAll());
        map.put("employers", employerRepository.findAll());
        map.put("teachers", teacherRepository.findAll());
        return "main/administration_list";
    }
}