package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.repositories.ExpositionRepository;
import ru.kpfu.itis.artgallery.services.AuthenticationService;
import ru.kpfu.itis.artgallery.services.ExpositionService;


@Controller
@RequestMapping(value = "/expositions")
public class ExpositionController {

    private ExpositionService expositionService;
    private final AuthenticationService authenticationService;
    private ExpositionRepository expositionRepository;

    @Autowired
    public ExpositionController(ExpositionService expositionService, ExpositionRepository expositionRepository, AuthenticationService authenticationService) {
        this.expositionService = expositionService;
        this.expositionRepository = expositionRepository;
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "/{id}")
    public String getExhibit(Model model, @PathVariable Long id, Authentication authentication) {
        Exposition exposition = expositionRepository.getOne(id);
        model.addAttribute("user", authenticationService.getUserByAuthentication(authentication));
        model.addAttribute("model", exposition);
//        model.addAttribute("exhibits", expositionService.findSimilar(exhibit.getExposition()));
        return "expositions";
    }

}
