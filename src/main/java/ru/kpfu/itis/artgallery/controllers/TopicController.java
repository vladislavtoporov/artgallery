package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.Topic;
import ru.kpfu.itis.artgallery.repositories.TopicRepository;

import java.util.List;


@Controller
@RequestMapping(value = "/topics")
public class TopicController {

    private TopicRepository topicRepository;

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping(value = "")
    public String getAll(Model model) {
        List<Topic> topics = topicRepository.findAll();
        model.addAttribute("topics", topics);
        return "topics";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Topic topic = topicRepository.getOne(id);
        model.addAttribute("topic", topic);
        return "topic";
    }

    @PostMapping(value = "/{id}/add")
    public String add(Topic topic) {
        topicRepository.save(topic);
        return "redirect:/topics";
    }

    @PostMapping(value = "/{id}/edit")
    public String edit(Topic topic) {
        topicRepository.save(topic);
        return "redirect:/topics";
    }

    @PostMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        topicRepository.delete(topicRepository.getOne(id));
        return "redirect:/topics";
    }
}
