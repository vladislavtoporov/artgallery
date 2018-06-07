package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.artgallery.forms.LoginForm;
import ru.kpfu.itis.artgallery.forms.TokenDto;
import ru.kpfu.itis.artgallery.services.AuthenticationService;
import ru.kpfu.itis.artgallery.services.LoginService;
import ru.kpfu.itis.artgallery.validators.LoginFormValidator;

//import ru.kpfu.itis.artgallery.providers.AuthProvider;

@Controller
public class AuthController {
    private AuthenticationService authenticationService;
    //    private AuthProvider authProvider;
    private LoginFormValidator loginFormValidator;
    private LoginService loginService;

    @Autowired
    public AuthController(AuthenticationService service, LoginFormValidator loginFormValidator, LoginService loginService) {
        this.authenticationService = service;
        this.loginFormValidator = loginFormValidator;
        this.loginService = loginService;
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

    @PostMapping("/jwt/login")
    @ResponseBody
    public ResponseEntity<?> login(LoginForm loginPassword, @RequestParam(value = "jwt",
            required = false, defaultValue = "true") boolean isJwtEnabled) {
        TokenDto tokenDto = loginService.login(loginPassword, isJwtEnabled);
        System.out.println(tokenDto);
        return ResponseEntity.ok(tokenDto);
    }

    @GetMapping("/jwt/signIn")
    public String jwt(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/jwt/hello";
        }
        return "jwt_signIn";
    }

    @GetMapping("/client/signIn")
    public String loginWithoutCSRF(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/api/exhibits";
        }
        return "client_signIn";
    }

    @GetMapping("/client/signIn/error")
    public String error2(Model model) {
        model.addAttribute("error", "Неправильный логин или пароль");
        return "client_signIn";
    }

    @GetMapping("/jwt/hello")
    @ResponseBody
    public String hello(Authentication authentication) {
        return authenticationService.getUserByAuthentication(authentication).getLogin();
    }



}
