package ru.kpfu.itis.artgallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.services.ExhibitService;


@Controller
@RequestMapping(value = "/exhibits")
public class ExhibitController {

    private ExhibitService exhibitRepository;

    public ExhibitController(ExhibitService exhibitRepository) {
        this.exhibitRepository = exhibitRepository;
    }


}
