package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.repositories.ExhibitRepository;

import java.util.List;


@Controller
@RequestMapping(value = "/exhibits")
public class ExhibitController {

    private ExhibitRepository exhibitRepository;
    @Autowired
    public void setExhibitRepository(ExhibitRepository exhibitRepository) {
        this.exhibitRepository = exhibitRepository;
    }

    @GetMapping(value = "")
    public String getAll(Model model) {
        List<Exhibit> exhibits =  exhibitRepository.findAll();
        model.addAttribute("exhibit", exhibits);
        return "exhibits";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Exhibit exhibit = exhibitRepository.getOne(id);
        model.addAttribute("exhibit", exhibit);
        return "exhibit";
    }

    @PostMapping(value = "/{id}/add")
    public String add(Exhibit exhibit) {
        exhibitRepository.save(exhibit);
        return "redirect:/exhibits";
    }

    @PostMapping(value = "/{id}/edit")
    public String edit(Exhibit user) {
        exhibitRepository.save(user);
        return "redirect:/exhibits";
    }

    @PostMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        exhibitRepository.delete(exhibitRepository.getOne(id));
        return "redirect:/exhibits";
    }
}
