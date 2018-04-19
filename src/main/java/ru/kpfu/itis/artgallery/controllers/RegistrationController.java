package ru.kpfu.itis.artgallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.artgallery.forms.UserRegistrationForm;
import ru.kpfu.itis.artgallery.services.UserService;
import ru.kpfu.itis.artgallery.validators.UserRegistrationFormValidator;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserService userService;
    private UserRegistrationFormValidator userRegistrationFormValidator;

    public RegistrationController(UserService userService, UserRegistrationFormValidator userRegistrationFormValidator) {
        this.userService = userService;
        this.userRegistrationFormValidator = userRegistrationFormValidator;
    }

    @InitBinder("userForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(userRegistrationFormValidator);
    }


    @PostMapping(value = "/signUp")
    public String signUp(@Valid @ModelAttribute("userForm") UserRegistrationForm userRegistrationForm,
                         BindingResult errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUp";
        }
        userService.register(userRegistrationForm);
        return "redirect:/signIn";
    }

    @GetMapping(value = "/signUp")
    public String getSignUpPage() {
        return "sign_up";
    }
}
