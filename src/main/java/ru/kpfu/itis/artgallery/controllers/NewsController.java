package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.News;
import ru.kpfu.itis.artgallery.repositories.NewsRepository;

import java.util.List;


@Controller
@RequestMapping(value = "/news")
public class NewsController {

    private NewsRepository newsRepository;
    @Autowired
    public void setNewsRepository(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping(value = "")
    public String getAll(Model model) {
        List<News> news =  newsRepository.findAll();
        model.addAttribute("news", news);
        return "news";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        News news = newsRepository.getOne(id);
        model.addAttribute("news", news);
        return "news";
    }

    @PostMapping(value = "/{id}/add")
    public String add(News news) {
        newsRepository.save(news);
        return "redirect:/news";
    }

    @PostMapping(value = "/{id}/edit")
    public String edit(News user) {
        newsRepository.save(user);
        return "redirect:/news";
    }

    @PostMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        newsRepository.delete(newsRepository.getOne(id));
        return "redirect:/news";
    }
}
