package ru.kpfu.itis.artgallery.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.artgallery.enums.Role;
import ru.kpfu.itis.artgallery.enums.TicketStatus;
import ru.kpfu.itis.artgallery.forms.*;
import ru.kpfu.itis.artgallery.models.*;
import ru.kpfu.itis.artgallery.repositories.*;
import ru.kpfu.itis.artgallery.services.AuthenticationService;
import ru.kpfu.itis.artgallery.services.EmailService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/rest")
public class EntityPostRestController {
    private ExhibitRepository exhibitRepository;
    private ExpositionRepository expositionRepository;
    private NewsRepository newsRepository;
    private PostRepository postRepository;
    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private AuthenticationService authenticationService;
    private PrivateMessageRepository privateMessageRepository;
    private TicketRepository ticketRepository;
    private FileRepository fileRepository;
    @Value("${CLOUDINARY_URL}")
    private String CLOUDINARY_URL;
    private EmailService emailService;


    @Autowired
    public EntityPostRestController(ExhibitRepository exhibitRepository,
                                    ExpositionRepository expositionRepository,
                                    NewsRepository newsRepository,
                                    PostRepository postRepository,
                                    TopicRepository topicRepository,
                                    UserRepository userRepository,
                                    AuthenticationService authenticationService,
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
        this.authenticationService = authenticationService;
        this.privateMessageRepository = privateMessageRepository;
        this.fileRepository = fileRepository;
        this.ticketRepository = ticketRepository;
        this.emailService = emailService;
    }

    @PostMapping(value = "/exhibits/add")
    public ResponseEntity<?> exhibitAdd(@Valid @RequestBody ExhibitForm exhibitForm,
                                        Authentication authentication, BindingResult errors) {

        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
        Exposition exposition = expositionRepository.getOne(exhibitForm.getExpositionId());
        User author = authenticationService.getUserByAuthentication(authentication);

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
    public ResponseEntity<?> exhibitEdit(@Valid @RequestBody ExhibitForm exhibitForm, BindingResult errors,
                                         Authentication authentication) {
        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
        Exhibit exhibit = exhibitRepository.getOne(exhibitForm.getId());
        Exposition exposition = expositionRepository.getOne(exhibitForm.getExpositionId());
        exhibit.setExposition(exposition);
        User author = authenticationService.getUserByAuthentication(authentication);
        exhibit.setAuthor(author);

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

    ///

    @PostMapping(value = "/expositions/add")
    public ResponseEntity<?> expositionAdd(@Valid @RequestBody ExpositionForm expositionForm,
                                           BindingResult errors, Authentication authentication) {
        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
        User owner = authenticationService.getUserByAuthentication(authentication);
        Exposition exposition = new Exposition(expositionForm, owner);

        try {
            expositionRepository.save(exposition);
            result.setMsg("success");
        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/expositions/{id}/edit")
    public ResponseEntity<?> expositionEdit(@Valid @RequestBody ExpositionForm expositionForm,
                                            @PathVariable Long id, BindingResult errors,
                                            Authentication authentication) {
        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
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
    @PostMapping(value = "news/add")
    public ResponseEntity<?> newsAdd(@Valid @RequestBody News news, BindingResult errors, Authentication authentication) {
        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
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
    public ResponseEntity<?> newsEdit(@Valid @RequestBody News newsForm, BindingResult errors,
                                      @PathVariable Long id,
                                      Authentication authentication) {
        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
        User user = authenticationService.getUserByAuthentication(authentication);
        News news = newsRepository.getOne(id);
        news.setPreview(newsForm.getPreview());
        news.setContent(newsForm.getContent());
        news.setHeader(newsForm.getHeader());
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

    //

    @PostMapping(value = "/tickets/add")
    public ResponseEntity<?> ticketAdd(@Valid @RequestBody Ticket ticket, BindingResult errors,
                                       Authentication authentication) {
        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
        User user = authenticationService.getUserByAuthentication(authentication);
        ticket.setRecipient(user);
        ticket.setTicketStatus(TicketStatus.NEW);
        try {
            ticketRepository.save(ticket);
            result.setMsg("success");

        } catch (Exception e) {
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/tickets/{id}/edit")
    public ResponseEntity<?> ticketEdit(@Valid @RequestBody Ticket answerForm,
                                        BindingResult errors, @PathVariable Long id, Authentication authentication) {
        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
        Ticket ticket = ticketRepository.getOne(id);
        User user = authenticationService.getUserByAuthentication(authentication);
        ticket.setManager(user);
        ticket.setTicketStatus(TicketStatus.FEEDBACK);
        ticket.setAnswer(answerForm.getAnswer());
        try {
            ticketRepository.save(ticket);
            result.setMsg("success");
            emailService.sendSimpleMessage(ticket.getRecipient().getLogin(),
                    ticket.getHeader(), ticket.getAnswer());
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    ///
    @PostMapping(value = "topics/add")
    public String topicAdd(@Valid @RequestBody Topic topic, BindingResult errors) {
        topicRepository.save(topic);
        return "redirect:/rest/topics";
    }

    @PostMapping(value = "topics/{id}/edit")
    public String topicEdit(@Valid @RequestBody Topic topic, BindingResult errors) {
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
    @PostMapping(value = "posts/add")
    public String postAdd(@Valid @RequestBody Post post, BindingResult errors) {
        postRepository.save(post);
        return "redirect:/rest/posts";
    }

    @PostMapping(value = "posts/{id}/edit")
    public String postEdit(@Valid @RequestBody Post post, BindingResult errors) {
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


    @PostMapping(value = "users/{id}/edit")
    public ResponseEntity<?> userEdit(@Valid @RequestBody UserForm userForm, BindingResult errors, @PathVariable Long id) {
        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
        User user = userRepository.getOne(id);
        System.out.println(userForm.getRole());
        user.setRole(Role.valueOf(userForm.getRole().toUpperCase()));
        System.out.println(user);
        try {
            userRepository.save(user);
            result.setMsg("success");
        } catch (Exception e) {
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

    @PostMapping(value = "files/{id}/delete")
    public ResponseEntity<?> fileDelete(@PathVariable("id") Long id) {
        AjaxForm result = new AjaxForm();
        try {
            File file = fileRepository.getOne(id);
            Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
            String publicId = file.getUpload().getPublicId();
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            fileRepository.delete(id);
            result.setMsg("success");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "privateMessages/add")
    public ResponseEntity<?> privateMessageAdd(@Valid @RequestBody PrivateMessageForm privateMessageForm,
                                               BindingResult errors, Authentication authentication) {
        AjaxForm result = new AjaxForm();
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(result);
        }
        try {
            User sender = authenticationService.getUserByAuthentication(authentication);
            User recipient = userRepository.findOneByLogin(privateMessageForm.getRecipient()).get();
            PrivateMessage privateMessage = new PrivateMessage(privateMessageForm, sender, recipient);
            privateMessageRepository.save(privateMessage);
            result.setMsg("success");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg("badRequest");
        }
        return ResponseEntity.ok(result);
    }

}
