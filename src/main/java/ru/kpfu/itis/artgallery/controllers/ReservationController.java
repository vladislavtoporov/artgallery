package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.artgallery.forms.ReservationForm;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.repositories.ExpositionRepository;
import ru.kpfu.itis.artgallery.reservationApplication.docGenerator.Generator;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class ReservationController {
    private final ExpositionRepository expositionRepository;

    @Autowired
    public ReservationController(ExpositionRepository expositionRepository) {
        this.expositionRepository = expositionRepository;
    }

    @GetMapping(value = "/reservation")
    public String reservationGet(Model model) {
        List<Exposition> expositions = expositionRepository.findAllByFinishAfter(new Date());
        model.addAttribute("expositions", expositions);
        if (model.asMap().get("form") == null)
            model.addAttribute("form", new ReservationForm());
        return "reservation";
    }

    @PostMapping(value = "/reservation")
    public String reservationGet(@Valid @ModelAttribute ReservationForm reservationForm, RedirectAttributes attributes) {
        attributes.addFlashAttribute("form", reservationForm);
        Generator generator = new Generator(
                reservationForm.getCustomer(),
                reservationForm.getNumberOfVisitors(),
                reservationForm.getDate(),
                reservationForm.getTime(),
                reservationForm.getCity(),
                reservationForm.getExposition()
        );
        String ticket = generator.fillData();
        attributes.addFlashAttribute("ticket", ticket);
        System.out.println(ticket);
        return "redirect:/reservation";
    }

    @GetMapping(value = "/expositions/plan")
    public String expositionGet(Model model) {
        List<Exposition> expositions = expositionRepository.findAllByFinishAfter(new Date());
        model.addAttribute("expositions", expositions);
        return "expositionPlan";
    }

    @PostMapping(value = "/expositions/plan")
    public String expositionPost(@Valid @ModelAttribute Exposition exposition, RedirectAttributes attributes) {
        Generator generator = new Generator(expositionRepository.getOne(exposition.getId()));
        String ticket = generator.createProgram();
        attributes.addFlashAttribute("ticket", ticket);
        System.out.println(ticket);
        return "redirect:/expositions/plan";
    }
}
