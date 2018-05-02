package ru.kpfu.itis.artgallery.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.artgallery.enums.Role;
import ru.kpfu.itis.artgallery.forms.AjaxForm;
import ru.kpfu.itis.artgallery.forms.ExhibitForm;
import ru.kpfu.itis.artgallery.forms.ExpositionForm;
import ru.kpfu.itis.artgallery.forms.UserForm;
import ru.kpfu.itis.artgallery.models.*;
import ru.kpfu.itis.artgallery.repositories.*;
import ru.kpfu.itis.artgallery.services.AuthenticationService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class EntityRestController {
    private ExhibitRepository exhibitRepository;
    private ExpositionRepository expositionRepository;
    private NewsRepository newsRepository;
    private PostRepository postRepository;
    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private AuthenticationService authenticationService;
    private static final Logger log = Logger.getLogger(EntityRestController.class);

    @Autowired
    public EntityRestController(ExhibitRepository exhibitRepository, ExpositionRepository expositionRepository, NewsRepository newsRepository, PostRepository postRepository, TopicRepository topicRepository, UserRepository userRepository, AuthenticationService authenticationService) {
        this.exhibitRepository = exhibitRepository;
        this.expositionRepository = expositionRepository;
        this.newsRepository = newsRepository;
        this.postRepository = postRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "/exhibits")
    public List exhibitGetAll() {
        log.info("exhibitGetAll is executed");
        return exhibitRepository.findAll();
    }

    @GetMapping(value = "/exhibits/{id}")
    public Exhibit exhibitGetById(@PathVariable("id") Long id) {
        log.info("exhibitGetById is executed");
        return exhibitRepository.getOne(id);
    }

    //    @PreAuthorize("hasAnyAuthority(\"ADMIN\", \"STAFF\")")
    @PostMapping(value = "/exhibits/add")
    public ResponseEntity<?> exhibitAdd(@RequestBody ExhibitForm exhibitForm,
                                        Authentication authentication) {
        log.info("exhibitAdd is executed");
        AjaxForm result = new AjaxForm();
        Exposition exposition = expositionRepository.getOne(exhibitForm.getExpositionId());
        User author = authenticationService.getUserByAuthentication(authentication);
        Exhibit exhibit = new Exhibit(exhibitForm, author, exposition);
        System.out.println(exhibit);
        try {
            exhibitRepository.save(exhibit);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("exhibitAdd error", e);
            result.setMsg("badRequest");

        }
        return ResponseEntity.ok(result);

    }

    @PostMapping(value = "/exhibits/{id}/edit")
    public ResponseEntity<?> exhibitEdit(@RequestBody ExhibitForm exhibitForm,
                                         Authentication authentication) {
        log.info("exhibitAdd is executed");
        AjaxForm result = new AjaxForm();
        Exposition exposition = expositionRepository.getOne(exhibitForm.getExpositionId());
        User author = authenticationService.getUserByAuthentication(authentication);
        Exhibit exhibit = exhibitRepository.getOne(exhibitForm.getId());
        exhibit.setExposition(exposition);
        exhibit.setAuthor(author);
        exhibit.setName(exhibitForm.getName());
        exhibit.setContent(exhibitForm.getContent());
        try {
            exhibitRepository.save(exhibit);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("exhibitAdd error", e);
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/exhibits/{id}/delete")
    public ResponseEntity<?> exhibitDelete(@PathVariable("id") Long id) {
        AjaxForm result = new AjaxForm();
        try {
            exhibitRepository.delete(id);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    ///

    @GetMapping(value = "/expositions")
    public List expositionGetAll() {
        return expositionRepository.findAll();
    }

    @GetMapping(value = "/expositions/{id}")
    public Exposition expositionGetById(@PathVariable("id") Long id) {
        return expositionRepository.getOne(id);
    }

    @PostMapping(value = "/expositions/add")
    public ResponseEntity<?> expositionAdd(@RequestBody Exposition exposition,
                                           Authentication authentication) {
        AjaxForm result = new AjaxForm();
        User owner = authenticationService.getUserByAuthentication(authentication);
        exposition.setOwner(owner);
        try {
            expositionRepository.save(exposition);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/expositions/{id}/edit")
    public ResponseEntity<?> expositionEdit(@RequestBody ExpositionForm expositionForm,
                                            @PathVariable Long id,
                                            Authentication authentication) {
        AjaxForm result = new AjaxForm();
        User owner = authenticationService.getUserByAuthentication(authentication);
        Exposition exposition = expositionRepository.getOne(id);
        exposition.setName(expositionForm.getName());
        exposition.setDescription(exposition.getDescription());
        exposition.setOwner(owner);
        try {
            expositionRepository.save(exposition);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/expositions/{id}/delete")
    public ResponseEntity<?> expositionDelete(@PathVariable("id") Long id) {
        AjaxForm result = new AjaxForm();
        try {
            expositionRepository.delete(id);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    ///

    @GetMapping(value = "/news")
    public List newsGetAll() {
        return newsRepository.findAll();
    }

    @GetMapping(value = "news/{id}")
    public News newsGetById(@PathVariable("id") Long id) {
        return newsRepository.getOne(id);
    }

    @PostMapping(value = "news/add")
    public ResponseEntity<?> newsAdd(@RequestBody News news, Authentication authentication) {
        AjaxForm result = new AjaxForm();
        User user = authenticationService.getUserByAuthentication(authentication);
        news.setUser(user);
        try {
            newsRepository.save(news);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "news/{id}/edit")
    public ResponseEntity<?> newsEdit(@RequestBody News newsForm,
                                      @PathVariable Long id,
                                      Authentication authentication) {
        AjaxForm result = new AjaxForm();
        User user = authenticationService.getUserByAuthentication(authentication);
        News news = newsRepository.getOne(id);
        news.setPreview(newsForm.getPreview());
        news.setContent(newsForm.getContent());
        news.setUser(user);
        try {
            newsRepository.save(news);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "news/{id}/delete")
    public ResponseEntity<?> newsDelete(@PathVariable("id") Long id) {
        AjaxForm result = new AjaxForm();
        try {
            newsRepository.delete(id);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    ///

    @GetMapping(value = "/topics")
    public List topicGetAll() {
        return topicRepository.findAll();
    }

    @GetMapping(value = "topics/{id}")
    public Topic topicGetById(@PathVariable("id") Long id) {
        return topicRepository.getOne(id);
    }

    @PostMapping(value = "topics/add")
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
    public ResponseEntity<?> topicDelete(@PathVariable("id") Long id) {
        AjaxForm result = new AjaxForm();
        try {
            topicRepository.delete(id);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    ///

    @GetMapping(value = "/posts")
    public List postGetAll() {
        return postRepository.findAll();
    }

    @GetMapping(value = "posts/{id}")
    public Post postGetById(@PathVariable("id") Long id) {
        return postRepository.getOne(id);
    }

    @PostMapping(value = "posts/add")
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
    public ResponseEntity<?> postDelete(@PathVariable("id") Long id) {
        AjaxForm result = new AjaxForm();
        try {
            postRepository.delete(id);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    ///

    @GetMapping(value = "/users")
    public List userGetAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "users/{id}")
    public User userGetById(@PathVariable("id") Long id) {
        return userRepository.getOne(id);
    }

    @PostMapping(value = "users/add")
    public String userAdd(@RequestBody User user) {
        return "not allowed";
    }

    @PostMapping(value = "users/{id}/edit")
    public ResponseEntity<?> userEdit(@RequestBody UserForm userForm, @PathVariable Long id) {
        AjaxForm result = new AjaxForm();
        User user = userRepository.getOne(id);
        System.out.println(userForm.getRole());
        user.setRole(Role.valueOf(userForm.getRole().toUpperCase()));
        System.out.println(user);
        try {
            userRepository.save(user);
            result.setMsg("success");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "users/{id}/delete")
    public ResponseEntity<?> userDelete(@PathVariable("id") Long id) {
        AjaxForm result = new AjaxForm();
        try {
            userRepository.delete(id);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

}
