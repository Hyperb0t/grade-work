package ru.itis.jaboderzhateli.gradework.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;

@Controller
@RequestMapping("/")
public class LandingController {

    @PreAuthorize("permitAll()")
    @GetMapping
    public String getLanding(@AuthenticationPrincipal UserDetailsImpl userDetails, ModelMap map) {
        if(userDetails != null) {
            map.put("me", userDetails.getUser());
        }
        return "main/landing";
    }
}
