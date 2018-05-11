package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.artgallery.models.News;
import ru.kpfu.itis.artgallery.repositories.NewsRepository;
import ru.kpfu.itis.artgallery.services.AuthenticationService;

import java.util.List;

@Controller
public class MainController {
    private final AuthenticationService authenticationService;
    private NewsRepository newsRepository;

    @Autowired
    public MainController(NewsRepository newsRepository, AuthenticationService authenticationService) {
        this.newsRepository = newsRepository;
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "/")
    public String index(Model model, Authentication authentication) {
        List<News> news = newsRepository.findAll();
//        model.addAttribute("user",  authenticationService.getUserByAuthentication(authentication));
        model.addAttribute("news", news);
        return "index";
    }
}
