package ru.kpfu.itis.artgallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.repositories.ExhibitRepository;
import ru.kpfu.itis.artgallery.repositories.ExpositionRepository;
import ru.kpfu.itis.artgallery.services.AuthenticationService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private AuthenticationService service;
    private ExhibitRepository exhibitRepository;
    private ExpositionRepository expositionRepository;

    public AdminController(AuthenticationService service, ExhibitRepository exhibitRepository, ExpositionRepository expositionRepository) {
        this.service = service;
        this.exhibitRepository = exhibitRepository;
        this.expositionRepository = expositionRepository;
    }

    @GetMapping("/")
    public String admin() {
        return "redirect:/admin/exhibits/add";
    }

    @GetMapping("/exhibits/{id}/edit")
    public String exhibitEdit(@PathVariable Long id, Model model) {
        model.addAttribute("model", exhibitRepository.getOne(id));
        model.addAttribute("expositions", expositionRepository.findAll());
        return "exhibits.add";
    }

    @GetMapping("/exhibits/add")
    public String exhibitAdd(Model model) {
        model.addAttribute("expositions", expositionRepository.findAll());
        return "exhibits.add";
    }

    @GetMapping("/expositions/add")
    public String expositionAdd() {
        return "expositions.add";
    }

    @GetMapping("/expositions/{id}/edit")
    public String expositionEdit(@PathVariable Long id, Model model) {
        model.addAttribute("model", expositionRepository.getOne(id));
        return "expositions.add";
    }

    @GetMapping("/news/add")
    public String newsAdd() {
        return "news.add";
    }

}
