package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.services.ExhibitService;
import ru.kpfu.itis.artgallery.services.ExpositionService;

import java.util.Optional;

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
    public String searchGet(@RequestParam(required = false) String query,
                            @RequestParam(required = false, defaultValue = "author") String sort,
                            @RequestParam(required = false, defaultValue = "exhibit") String type,
                            @RequestParam(required = false, defaultValue = "0") Integer page,
                            Model model) {
        if (query == null) {
            query = (String) model.asMap().getOrDefault("query", "");
        }
        model.addAttribute("query", query);
        model.addAttribute("sort", sort);
        model.addAttribute("type", type);
        if ("exhibit".equals(type)) {
            Page<Exhibit> exhibits = exhibitService.findAllByQuerry(query, sort, page);
            model.addAttribute("exhibits", exhibits);
        }
        if ("exposition".equals(type)) {
            Page<Exposition> expositions = expositionService.findAllByQuerry(query, sort, page);
            model.addAttribute("expositions", expositions);
        }
        model.addAttribute("toplist", exhibitService.findLast());
        return "search";
    }


    @PostMapping(value = "/search/tag")
    public String getByTag(@RequestParam String query, RedirectAttributes attributes) {
        Optional<Exhibit> exhibit = exhibitService.findByName(query);
        if (exhibit.isPresent()) {
            return "redirect:/exhibits/" + exhibit.get().getId();
        } else {
            attributes.addFlashAttribute("query", query);
            return "redirect:/search";
        }
    }
}
