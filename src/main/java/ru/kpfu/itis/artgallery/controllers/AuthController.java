package ru.kpfu.itis.artgallery.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.artgallery.providers.AuthProvider;
import ru.kpfu.itis.artgallery.services.AuthenticationService;
import ru.kpfu.itis.artgallery.validators.LoginFormValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    private AuthenticationService service;
    private AuthProvider authProvider;
    private LoginFormValidator loginFormValidator;

    public AuthController(AuthenticationService service, AuthProvider authProvider, LoginFormValidator loginFormValidator) {
        this.service = service;
        this.authProvider = authProvider;
        this.loginFormValidator = loginFormValidator;
    }

    @InitBinder("userForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(loginFormValidator);
    }

    @GetMapping("/signIn")
    public String login(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "signIn";
    }

    @GetMapping("/signIn/error")
    public String error(Model model) {
        model.addAttribute("error", "Неправильный логин или пароль");
        return "signIn";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, Authentication authentication) {
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }


//    @GetMapping("/")
//    public String root(Authentication authentication) {
//        if (authentication != null) {
//            return "redirect:/cosplays";
//            /*
//            User user = service.getUserByAuthentication(authentication);
//            if (user.getRole().equals(Role.USER)) {
//                return "redirect:/user/profile";
//            } else if (user.getRole().equals(Role.ADMIN)) {
//                return "redirect:/admin/users";
//                */
//        }
//        return "redirect:/login";
//
//    }

//    @GetMapping("/user/profile")
//    public String getProfilePage(Authentication authentication, @ModelAttribute("model") ModelMap model) {
//        model.addAttribute(service.getUserByAuthentication(authentication));
//        return "profile";
//    }
}
