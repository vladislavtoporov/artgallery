package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.repositories.ExpositionRepository;

import java.util.List;


@Controller
@RequestMapping(value = "/exposition")
public class ExpositionController {

    private ExpositionRepository expositionRepository;
    @Autowired
    public void setExpositionRepository(ExpositionRepository expositionRepository) {
        this.expositionRepository = expositionRepository;
    }

    @GetMapping(value = "")
    public String getAll(Model model) {
        List<Exposition> expositions =  expositionRepository.findAll();
        model.addAttribute("expositions", expositions);
        return "expositions";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Exposition exposition = expositionRepository.getOne(id);
        model.addAttribute("exposition", exposition);
        return "exposition";
    }

    @PostMapping(value = "/{id}/add")
    public String add(Exposition exposition) {
        expositionRepository.save(exposition);
        return "redirect:/expositions";
    }

    @PostMapping(value = "/{id}/edit")
    public String edit(Exposition user) {
        expositionRepository.save(user);
        return "redirect:/expositions";
    }

    @PostMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        expositionRepository.delete(expositionRepository.getOne(id));
        return "redirect:/expositions";
    }
}
