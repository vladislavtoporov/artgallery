package ru.kpfu.itis.artgallery.controllers.advice;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kpfu.itis.artgallery.services.AuthenticationService;

@ControllerAdvice()
public class AdviceController {
    private AuthenticationService authenticationService;

    public AdviceController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ModelAttribute
    public void setUser(Authentication authentication, Model model) {
        if (authentication != null) {
            model.addAttribute("user", authenticationService.getUserByAuthentication(authentication));
        }
    }
}
