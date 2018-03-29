package ru.kpfu.itis.artgallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.services.ExpositionService;


@Controller
@RequestMapping(value = "/expositions")
public class ExpositionController {

    private ExpositionService expositionService;

    public ExpositionController(ExpositionService expositionService) {
        this.expositionService = expositionService;
    }


}
