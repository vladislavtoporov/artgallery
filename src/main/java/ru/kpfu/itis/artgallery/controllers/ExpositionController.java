package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.services.ExpositionService;


@Controller
@RequestMapping(value = "/expositions")
public class ExpositionController {

    private ExpositionService expositionService;

    @Autowired
    public ExpositionController(ExpositionService expositionService) {
        this.expositionService = expositionService;
    }

    @GetMapping(value = "")
    public String getExhibit(Model model) {
        model.addAttribute("model", expositionService.findAll(0));
        return "expositions";
    }
    @GetMapping(value = "/{id}")
    public String getExhibit(Model model, @PathVariable Long id) {
        Exposition exposition = expositionService.getOne(id);
        model.addAttribute("model", exposition);
        model.addAttribute("expositions", expositionService.findSimilar(exposition));
        return "exposition";
    }

}
