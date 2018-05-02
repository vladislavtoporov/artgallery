package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.models.News;
import ru.kpfu.itis.artgallery.models.User;
import ru.kpfu.itis.artgallery.repositories.ExhibitRepository;
import ru.kpfu.itis.artgallery.repositories.ExpositionRepository;
import ru.kpfu.itis.artgallery.repositories.NewsRepository;
import ru.kpfu.itis.artgallery.repositories.UserRepository;
import ru.kpfu.itis.artgallery.services.AuthenticationService;
import ru.kpfu.itis.artgallery.services.PrivateMessageService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private AuthenticationService authService;
    private ExhibitRepository exhibitRepository;
    private ExpositionRepository expositionRepository;
    private NewsRepository newsRepository;
    private UserRepository userRepository;
    private PrivateMessageService privateMessageService;

    @Autowired
    public AdminController(AuthenticationService service, ExhibitRepository exhibitRepository, ExpositionRepository expositionRepository, NewsRepository newsRepository, UserRepository userRepository, PrivateMessageService privateMessageService) {
        this.authService = service;
        this.exhibitRepository = exhibitRepository;
        this.expositionRepository = expositionRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.privateMessageService = privateMessageService;
    }

    @GetMapping("/")
    public String admin() {
        return "redirect:/admin/exhibits/add";
    }

    @GetMapping("/exhibits/{id}/edit")
    public String exhibitEdit(@PathVariable Long id, Model model, Authentication authentication) {
        model.addAttribute("user", authService.getUserByAuthentication(authentication));
        model.addAttribute("model", exhibitRepository.getOne(id));
        model.addAttribute("expositions", expositionRepository.findAll());
        return "admin/exhibits.add";
    }

    @GetMapping("/exhibits/add")
    public String exhibitAdd(Model model, Authentication authentication) {
        model.addAttribute("user", authService.getUserByAuthentication(authentication));
        model.addAttribute("model", new Exhibit());
        model.addAttribute("expositions", expositionRepository.findAll());
        return "admin/exhibits.add";
    }

    @GetMapping("/exhibits")
    public String exhibits(Model model, Authentication authentication) {
        model.addAttribute("user", authService.getUserByAuthentication(authentication));
        model.addAttribute("model", exhibitRepository.findAll());
        return "admin/exhibits";
    }

    @GetMapping("/expositions/add")
    public String expositionAdd(Model model, Authentication authentication) {
        model.addAttribute("user", authService.getUserByAuthentication(authentication));
        model.addAttribute("model", new Exposition());
        return "admin/expositions.add";
    }

    @GetMapping("/expositions/{id}/edit")
    public String expositionEdit(@PathVariable Long id, Model model, Authentication authentication) {
        model.addAttribute("user", authService.getUserByAuthentication(authentication));
        model.addAttribute("model", expositionRepository.getOne(id));
        return "admin/expositions.add";
    }

    @GetMapping("/news/add")
    public String newsAdd(Model model, Authentication authentication) {
        model.addAttribute("user", authService.getUserByAuthentication(authentication));
        model.addAttribute("model", new News());
        return "admin/news.add";
    }

    @GetMapping("/news/{id}/edit")
    public String newsEdit(Model model, @PathVariable Long id, Authentication authentication) {
        model.addAttribute("user", authService.getUserByAuthentication(authentication));
        model.addAttribute("model", newsRepository.getOne(id));
        return "admin/news.add";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("list", expositionRepository.findAll());
        return "search";
    }

    @GetMapping("/users")
    public String users(Model model, Authentication authentication) {
        model.addAttribute("user", authService.getUserByAuthentication(authentication));
        model.addAttribute("model", userRepository.findAll());
        return "admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String userEdit(Model model, @PathVariable Long id, Authentication authentication) {
        model.addAttribute("user", authService.getUserByAuthentication(authentication));
        model.addAttribute("model", userRepository.getOne(id));
        return "admin/users.add";
    }

    @GetMapping("/expositions")
    public String expositions(Model model, Authentication authentication) {
        model.addAttribute("user", authService.getUserByAuthentication(authentication));
        model.addAttribute("model", expositionRepository.findAll());
        return "admin/expositions";
    }

    @GetMapping("/news")
    public String news(Model model, Authentication authentication) {
        model.addAttribute("user", authService.getUserByAuthentication(authentication));
        model.addAttribute("model", newsRepository.findAll());
        return "admin/news";
    }

    @GetMapping("/compose")
    public String compose(Model model, Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);
        model.addAttribute("user", user);
        model.addAttribute("model", newsRepository.findAll());
        return "admin/compose";
    }

    @GetMapping("/mailbox")
    public String mailbox(Model model, Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);
        model.addAttribute("user", user);
        model.addAttribute("model", newsRepository.findAll());
        return "admin/mailbox";
    }

    @GetMapping("/readMail")
    public String readMail(Model model, Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);
        model.addAttribute("user", user);
        model.addAttribute("model", newsRepository.findAll());
        return "admin/read-mail";
    }
}
