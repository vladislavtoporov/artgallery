package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.services.ExhibitService;
import ru.kpfu.itis.artgallery.services.ExpositionService;


@Controller
@RequestMapping(value = "/exhibits")
public class ExhibitController {

    private final ExpositionService expositionService;
    private ExhibitService exhibitService;

    @Autowired
    public ExhibitController(ExhibitService exhibitService, ExpositionService expositionService) {
        this.exhibitService = exhibitService;
        this.expositionService = expositionService;
    }

    @GetMapping(value = "exhibits/{id}")
    public String getExhibit(Model model, @PathVariable Long id) {
        Exhibit exhibit = exhibitService.getOne(id);
        model.addAttribute("model", exhibit);
//        model.addAttribute("exhibits", expositionService.findSimilar(exhibit.getExposition()));
        return "exhibits";
    }
}
