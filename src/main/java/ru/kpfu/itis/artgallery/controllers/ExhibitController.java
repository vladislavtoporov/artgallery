package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.services.AuthenticationService;
import ru.kpfu.itis.artgallery.services.ExhibitService;
import ru.kpfu.itis.artgallery.services.ExpositionService;


@Controller
@RequestMapping(value = "/exhibits")
public class ExhibitController {

    private final ExpositionService expositionService;
    private ExhibitService exhibitService;
    private final AuthenticationService authenticationService;


    @Autowired
    public ExhibitController(ExhibitService exhibitService, ExpositionService expositionService, AuthenticationService authenticationService) {
        this.exhibitService = exhibitService;
        this.expositionService = expositionService;
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "/{id}")
    public String getExhibit(Model model, @PathVariable Long id, Authentication authentication) {
        Exhibit exhibit = exhibitService.getOne(id);
        model.addAttribute("model", exhibit);
        model.addAttribute("user", authenticationService.getUserByAuthentication(authentication));
//        model.addAttribute("exhibits", expositionService.findSimilar(exhibit.getExposition()));
        return "exhibits";
    }
}
