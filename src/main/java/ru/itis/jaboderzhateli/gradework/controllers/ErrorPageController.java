package ru.itis.jaboderzhateli.gradework.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.jaboderzhateli.gradework.security.UserDetailsImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorPageController implements ErrorController {

    @GetMapping("/error")
    public String getErrorPage(@AuthenticationPrincipal UserDetailsImpl userDetails, ModelMap map, HttpServletRequest request) {
        if(userDetails != null) {
            map.put("me", userDetails.getUser());
        }
        map.put("code", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return "error_page";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
