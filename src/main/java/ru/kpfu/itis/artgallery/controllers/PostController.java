package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.Post;
import ru.kpfu.itis.artgallery.repositories.PostRepository;

import java.util.List;


@Controller
@RequestMapping(value = "/posts")
public class PostController {

    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping(value = "")
    public String getAll(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Post post = postRepository.getOne(id);
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping(value = "/{id}/add")
    public String add(Post post) {
        postRepository.save(post);
        return "redirect:/posts";
    }

    @PostMapping(value = "/{id}/edit")
    public String edit(Post post) {
        postRepository.save(post);
        return "redirect:/posts";
    }

    @PostMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        postRepository.delete(postRepository.getOne(id));
        return "redirect:/posts";
    }
}
