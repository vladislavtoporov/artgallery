package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.UserRepository;

import java.util.List;


@Controller
@RequestMapping(value = "/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "")
    public String getAll(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        User user = userRepository.getOne(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping(value = "/{id}/add")
    public String add(User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @PostMapping(value = "/{id}/edit")
    public String edit(User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @PostMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userRepository.delete(userRepository.getOne(id));
        return "redirect:/users";
    }
}
