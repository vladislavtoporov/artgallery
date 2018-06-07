package ru.kpfu.itis.artgallery.controllers.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.PrivateMessageRepository;
import ru.kpfu.itis.artgallery.services.AuthenticationService;

@ControllerAdvice(basePackages = {"ru.kpfu.itis.artgallery.controllers.admin"})
public class AdminAdviceController {
    private AuthenticationService authenticationService;
    private PrivateMessageRepository privateMessageRepository;

    @Autowired
    public AdminAdviceController(AuthenticationService authenticationService, PrivateMessageRepository privateMessageRepository) {
        this.authenticationService = authenticationService;
        this.privateMessageRepository = privateMessageRepository;
    }

    @ModelAttribute
    public void setMessages(Authentication authentication, Model model) {
        if (authentication != null) {
            User user = authenticationService.getUserByAuthentication(authentication);
            model.addAttribute("messages", privateMessageRepository.findAllByRecipientAndIsRead(user, false));
        }
    }
}
