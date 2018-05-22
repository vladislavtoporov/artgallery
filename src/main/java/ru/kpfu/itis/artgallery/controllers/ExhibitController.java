package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.File;
import ru.kpfu.itis.artgallery.services.ExhibitService;
import ru.kpfu.itis.artgallery.services.FileService;

import java.util.List;


@Controller
@RequestMapping(value = "/exhibits")
public class ExhibitController {
    private ExhibitService exhibitService;
    private FileService fileService;

    @Autowired
    public ExhibitController(ExhibitService exhibitService, FileService fileService) {
        this.exhibitService = exhibitService;
        this.fileService = fileService;
    }


    @GetMapping(value = "")
    public String getExhibits(Model model) {
        model.addAttribute("model", exhibitService.findAllSortByName(0));
        return "exhibits";
    }

    @GetMapping(value = "/{id}")
    public String getExhibit(Model model, @PathVariable Long id) {
        Exhibit exhibit = exhibitService.getOne(id);
        model.addAttribute("model", exhibit);
        List<File> images = fileService.findAllByExhibitAndContentTypeEquals(exhibit, "image");
        if (images.size() > 0) {
            model.addAttribute("picture", images.get(0));
            model.addAttribute("images", images.subList(1, images.size()));
        }
        model.addAttribute("videos",
                fileService.findAllByExhibitAndContentTypeEquals(exhibit, "video"));
        model.addAttribute("audios",
                fileService.findAllByExhibitAndContentTypeEquals(exhibit, "audio"));

        model.addAttribute("exhibits", exhibitService.findSimilar(exhibit));
        return "exhibit";
    }
}
