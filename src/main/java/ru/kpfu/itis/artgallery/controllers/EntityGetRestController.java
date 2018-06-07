package ru.kpfu.itis.artgallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.artgallery.forms.AjaxForm;
import ru.kpfu.itis.artgallery.forms.ExhibitForm;
import ru.kpfu.itis.artgallery.models.*;
import ru.kpfu.itis.artgallery.repositories.*;
import ru.kpfu.itis.artgallery.services.EmailService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EntityGetRestController {
    private ExhibitRepository exhibitRepository;
    private ExpositionRepository expositionRepository;
    private NewsRepository newsRepository;
    private PostRepository postRepository;
    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private TicketRepository ticketRepository;


    @Autowired
    public EntityGetRestController(ExhibitRepository exhibitRepository,
                                   ExpositionRepository expositionRepository,
                                   NewsRepository newsRepository,
                                   PostRepository postRepository,
                                   TopicRepository topicRepository,
                                   UserRepository userRepository,
                                   PrivateMessageRepository privateMessageRepository,
                                   FileRepository fileRepository,
                                   TicketRepository ticketRepository,
                                   EmailService emailService) {
        this.exhibitRepository = exhibitRepository;
        this.expositionRepository = expositionRepository;
        this.newsRepository = newsRepository;
        this.postRepository = postRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping(value = "/exhibits")
    public List exhibitGetAll() {
        return exhibitRepository.findAll();
    }

    @GetMapping(value = "/exhibits/{id}")
    public Exhibit exhibitGetById(@PathVariable("id") Long id) {
        return exhibitRepository.getOne(id);
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

    ///

    @GetMapping(value = "/news")
    public List newsGetAll() {
        return newsRepository.findAll();
    }

    @GetMapping(value = "news/{id}")
    public News newsGetById(@PathVariable("id") Long id) {
        return newsRepository.getOne(id);
    }


    //
    @GetMapping(value = "/tickets")
    public List ticketGetAll() {
        return ticketRepository.findAll();
    }

    @GetMapping(value = "/tickets/{id}")
    public Ticket ticketGetById(@PathVariable("id") Long id) {
        return ticketRepository.getOne(id);
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


    ///

    @GetMapping(value = "/posts")
    public List postGetAll() {
        return postRepository.findAll();
    }

    @GetMapping(value = "posts/{id}")
    public Post postGetById(@PathVariable("id") Long id) {
        return postRepository.getOne(id);
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


    @PostMapping(value = "/exhibits/add")
    public ResponseEntity<?> exhibitAdd(@Valid ExhibitForm exhibitForm,
                                        Authentication authentication, BindingResult errors) {

        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
        Exposition exposition = null;
        if (exhibitForm.getExpositionId() != null)
            exposition = expositionRepository.getOne(exhibitForm.getExpositionId());
        User author = userRepository.getOne(exhibitForm.getUserId());
        Exhibit exhibit = new Exhibit(exhibitForm, author, exposition);
        try {
            exhibitRepository.save(exhibit);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");

        }
        return ResponseEntity.ok(result);

    }

    @PostMapping(value = "/exhibits/{id}/edit")
    public ResponseEntity<?> exhibitEdit(@Valid ExhibitForm exhibitForm, BindingResult errors,
                                         Authentication authentication) {
        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
        Exhibit exhibit = exhibitRepository.getOne(exhibitForm.getId());
        if (exhibitForm.getExpositionId() != null) {
            Exposition exposition = expositionRepository.getOne(exhibitForm.getExpositionId());
            exhibit.setExposition(exposition);
        }
        exhibit.setName(exhibitForm.getName());
        exhibit.setContent(exhibitForm.getContent());
        try {
            exhibitRepository.save(exhibit);
            result.setMsg("success");
        } catch (Exception e) {
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


}
