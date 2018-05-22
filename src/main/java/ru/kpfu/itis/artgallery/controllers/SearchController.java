package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.services.ExhibitService;
import ru.kpfu.itis.artgallery.services.ExpositionService;

@Controller
public class SearchController {
    private ExpositionService expositionService;
    private ExhibitService exhibitService;

    @Autowired
    public SearchController(ExpositionService expositionService, ExhibitService exhibitService) {
        this.expositionService = expositionService;
        this.exhibitService = exhibitService;
    }

    @GetMapping(value = "/search")
    public String search() {
        return "search";
    }

    @PostMapping(value = "/search")
    public String searchPost(@RequestParam String querry,
                             @RequestParam(required = false) String sort,
                             @RequestParam(required = false) Boolean exhibitFlag,
                             @RequestParam(required = false) Boolean expositionFlag,
                             @RequestParam(required = false) Integer page,
                             Model model) {
        model.addAttribute("querry", querry);
        if (exhibitFlag != null) {
            Page<Exhibit> exhibits = exhibitService.findAllByQuerry(querry, sort, 0);
            model.addAttribute("exhibits", exhibits);
        }
        if (expositionFlag != null) {
            Page<Exposition> expositions = expositionService.findAllByQuerry(querry, sort, 0);
            model.addAttribute("expositions", expositions);
        }
        return "search";
    }

}
