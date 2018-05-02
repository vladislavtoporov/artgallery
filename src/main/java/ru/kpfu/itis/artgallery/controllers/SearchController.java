package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kpfu.itis.artgallery.repositories.ExpositionRepository;

@Controller
public class SearchController {
    @Autowired
    private ExpositionRepository expositionRepository;

    @GetMapping(value = "/search")
    public String search() {
        return "search";
    }

    @GetMapping(value = "/expositions/{id}")
    public String getExpositions(Model model, @PathVariable Long id) {
        model.addAttribute("model", expositionRepository.getOne(id));
        return "expositions";
    }
}
