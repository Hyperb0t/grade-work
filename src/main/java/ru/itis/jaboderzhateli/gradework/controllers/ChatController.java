package ru.itis.jaboderzhateli.gradework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String getChat() {
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/chat/{user-id}")
    public String getChatPage(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("user-id") String userId, ModelMap map) {
        map.put("me", userDetails.getUser());
        return "main/chat";
    }
}