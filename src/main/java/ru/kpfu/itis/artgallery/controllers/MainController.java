package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kpfu.itis.artgallery.models.News;
import ru.kpfu.itis.artgallery.repositories.FileRepository;
import ru.kpfu.itis.artgallery.repositories.NewsRepository;
import ru.kpfu.itis.artgallery.services.AuthenticationService;
import ru.kpfu.itis.artgallery.services.ExhibitService;

import java.util.List;

@Controller
public class MainController {
    private final AuthenticationService authenticationService;
    private NewsRepository newsRepository;
    private ExhibitService exhibitService;
    private FileRepository fileRepository;

    @Autowired
    public MainController(NewsRepository newsRepository, AuthenticationService authenticationService, ExhibitService exhibitService, FileRepository fileRepository) {
        this.newsRepository = newsRepository;
        this.authenticationService = authenticationService;
        this.exhibitService = exhibitService;
        this.fileRepository = fileRepository;
    }

    @GetMapping(value = "/")
    public String index(Model model, Authentication authentication) {
        List<News> news = newsRepository.findAll();
//        model.addAttribute("user",  authenticationService.getUserByAuthentication(authentication));
        model.addAttribute("news", news);
        model.addAttribute("carousel", fileRepository.findAllByContentTypeEquals(new PageRequest(0, 15, Sort.Direction.DESC, "ts"), "image"));

        return "index";
    }
}
