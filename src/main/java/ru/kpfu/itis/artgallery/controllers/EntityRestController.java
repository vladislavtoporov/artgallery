package ru.kpfu.itis.artgallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.artgallery.models.*;
import ru.kpfu.itis.artgallery.repositories.*;

import java.util.List;

@Controller
@RequestMapping(value = "/rest")
public class EntityRestController {
    private ExhibitRepository exhibitRepository;
    private ExpositionRepository expositionRepository;
    private NewsRepository newsRepository;
    private PostRepository postRepository;
    private TopicRepository topicRepository;
    private UserRepository userRepository;

    public EntityRestController(ExhibitRepository exhibitRepository, ExpositionRepository expositionRepository, NewsRepository newsRepository, PostRepository postRepository, TopicRepository topicRepository, UserRepository userRepository) {
        this.exhibitRepository = exhibitRepository;
        this.expositionRepository = expositionRepository;
        this.newsRepository = newsRepository;
        this.postRepository = postRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/exhibits")
    @ResponseBody
    public List exhibitGetAll() {
        return exhibitRepository.findAll();
    }

    @GetMapping(value = "exhibits/{id}")
    @ResponseBody
    public Exhibit exhibitGetById(@PathVariable("id") Long id) {
        return exhibitRepository.getOne(id);
    }

    @PostMapping(value = "exhibits/{id}/add")
    public String exhibitAdd(Exhibit exhibit) {
        exhibitRepository.save(exhibit);
        return "redirect:/rest/exhibits";
    }

    @PostMapping(value = "exhibits/{id}/edit")
    public String exhibitEdit(Exhibit user) {
        exhibitRepository.save(user);
        return "redirect:/rest/exhibits";
    }

    @PostMapping(value = "exhibits/{id}/delete")
    public String exhibitDelete(@PathVariable("id") Long id) {
        exhibitRepository.deleteById(id);
        return "redirect:/rest/exhibits";
    }

    ///

    @GetMapping(value = "/expositions")
    @ResponseBody
    public List expositionGetAll() {
        return expositionRepository.findAll();
    }

    @GetMapping(value = "expositions/{id}")
    @ResponseBody
    public Exposition expositionGetById(@PathVariable("id") Long id) {
        return expositionRepository.getOne(id);
    }

    @PostMapping(value = "expositions/{id}/add")
    public String expositionAdd(Exposition exposition) {
        expositionRepository.save(exposition);
        return "redirect:/rest/expositions";
    }

    @PostMapping(value = "expositions/{id}/edit")
    public String expositionEdit(Exposition exposition) {
        expositionRepository.save(exposition);
        return "redirect:/rest/expositions";
    }

    @PostMapping(value = "expositions/{id}/delete")
    public String expositionDelete(@PathVariable("id") Long id) {
        expositionRepository.deleteById(id);
        return "redirect:/rest/expositions";
    }

    ///

    @GetMapping(value = "/news")
    @ResponseBody
    public List newsGetAll() {
        return newsRepository.findAll();
    }

    @GetMapping(value = "news/{id}")
    @ResponseBody
    public News newsGetById(@PathVariable("id") Long id) {
        return newsRepository.getOne(id);
    }

    @PostMapping(value = "news/{id}/add")
    public String newsAdd(News news) {
        newsRepository.save(news);
        return "redirect:/rest/news";
    }

    @PostMapping(value = "news/{id}/edit")
    public String newsEdit(News news) {
        newsRepository.save(news);
        return "redirect:/rest/news";
    }

    @PostMapping(value = "news/{id}/delete")
    public String newsDelete(@PathVariable("id") Long id) {
        expositionRepository.deleteById(id);
        return "redirect:/rest/news";
    }

    ///

    @GetMapping(value = "/topics")
    @ResponseBody
    public List topicGetAll() {
        return topicRepository.findAll();
    }

    @GetMapping(value = "topics/{id}")
    @ResponseBody
    public Topic topicGetById(@PathVariable("id") Long id) {
        return topicRepository.getOne(id);
    }

    @PostMapping(value = "topics/{id}/add")
    public String topicAdd(Topic topic) {
        topicRepository.save(topic);
        return "redirect:/rest/topics";
    }

    @PostMapping(value = "topics/{id}/edit")
    public String topicEdit(Topic topic) {
        topicRepository.save(topic);
        return "redirect:/rest/topics";
    }

    @PostMapping(value = "topics/{id}/delete")
    public String topicDelete(@PathVariable("id") Long id) {
        topicRepository.deleteById(id);
        return "redirect:/rest/topics";
    }

    ///

    @GetMapping(value = "/posts")
    @ResponseBody
    public List postGetAll() {
        return postRepository.findAll();
    }

    @GetMapping(value = "posts/{id}")
    @ResponseBody
    public Post postGetById(@PathVariable("id") Long id) {
        return postRepository.getOne(id);
    }

    @PostMapping(value = "posts/{id}/add")
    public String postAdd(Post post) {
        postRepository.save(post);
        return "redirect:/rest/posts";
    }

    @PostMapping(value = "posts/{id}/edit")
    public String postEdit(Post post) {
        postRepository.save(post);
        return "redirect:/rest/posts";
    }

    @PostMapping(value = "posts/{id}/delete")
    public String postDelete(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        return "redirect:/rest/posts";
    }

    ///

    @GetMapping(value = "/users")
    @ResponseBody
    public List userGetAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "users/{id}")
    @ResponseBody
    public User userGetById(@PathVariable("id") Long id) {
        return userRepository.getOne(id);
    }

    @PostMapping(value = "users/{id}/add")
    public String userAdd(User user) {
        userRepository.save(user);
        return "redirect:/rest/users";
    }

    @PostMapping(value = "users/{id}/edit")
    public String userEdit(User user) {
        userRepository.save(user);
        return "redirect:/rest/users";
    }

    @PostMapping(value = "users/{id}/delete")
    public String userDelete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return "redirect:/rest/users";
    }

}
