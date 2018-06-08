package ru.kpfu.itis.artgallery.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.artgallery.enums.Role;
import ru.kpfu.itis.artgallery.enums.TicketStatus;
import ru.kpfu.itis.artgallery.models.*;
import ru.kpfu.itis.artgallery.repositories.*;
import ru.kpfu.itis.artgallery.services.AuthenticationService;
import ru.kpfu.itis.artgallery.services.PrivateMessageService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private AuthenticationService authService;
    private ExhibitRepository exhibitRepository;
    private ExpositionRepository expositionRepository;
    private NewsRepository newsRepository;
    private UserRepository userRepository;
    private PrivateMessageRepository privateMessageRepository;
    private TicketRepository ticketRepository;


    @Autowired
    public AdminController(AuthenticationService service, ExhibitRepository exhibitRepository, ExpositionRepository expositionRepository, NewsRepository newsRepository, UserRepository userRepository, PrivateMessageService privateMessageService, PrivateMessageRepository privateMessageRepository, TicketRepository ticketRepository) {
        this.authService = service;
        this.exhibitRepository = exhibitRepository;
        this.expositionRepository = expositionRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.privateMessageRepository = privateMessageRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("")
    public String admin() {
        return "redirect:/admin/exhibits/add";
    }

    @GetMapping("/exhibits/{id}/edit")
    public String exhibitEdit(@PathVariable Long id, Model model, Authentication authentication) {
        Exhibit exhibit = exhibitRepository.getOne(id);
        model.addAttribute("model", exhibit);
        model.addAttribute("expositions", expositionRepository.findAll());
        model.addAttribute("artists", userRepository.findAllByRoleIn(new Role[]{Role.ARTIST}));
        model.addAttribute("files", exhibit.getImages());
        return "admin/exhibits.add";
    }

    @GetMapping("/exhibits/add")
    public String exhibitAdd(Model model, Authentication authentication) {
        model.addAttribute("model", new Exhibit());
        model.addAttribute("artists", userRepository.findAllByRoleIn(new Role[]{Role.ARTIST}));
        model.addAttribute("expositions", expositionRepository.findAll());
        return "admin/exhibits.add";
    }

    @GetMapping("/exhibits")
    public String exhibits(Model model, Authentication authentication) {
        model.addAttribute("model", exhibitRepository.findAll());
        return "admin/exhibits";
    }

    @GetMapping("/expositions/add")
    public String expositionAdd(Model model, Authentication authentication) {
        model.addAttribute("artists", userRepository.findAllByRoleIn(new Role[]{Role.ARTIST}));
        model.addAttribute("model", new Exposition());
        return "admin/expositions.add";
    }

    @GetMapping("/expositions/{id}/edit")
    public String expositionEdit(@PathVariable Long id, Model model, Authentication authentication) {
        model.addAttribute("model", expositionRepository.getOne(id));
        model.addAttribute("artists", userRepository.findAllByRoleIn(new Role[]{Role.ARTIST}));
        return "admin/expositions.add";
    }
    @GetMapping("/news/add")
    public String newsAdd(Model model, Authentication authentication) {
        model.addAttribute("model", new News());
        return "admin/news.add";
    }

    //    @PreAuthorize("hasAnyAuthority({'SUPERVISER'})")
    @GetMapping("/news/{id}/edit")
    public String newsEdit(Model model, @PathVariable Long id, Authentication authentication) {
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
        model.addAttribute("model", userRepository.findAll());
        return "admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String userEdit(Model model, @PathVariable Long id, Authentication authentication) {
        model.addAttribute("model", userRepository.getOne(id));
        return "admin/users.add";
    }

    @GetMapping("/expositions")
    public String expositions(Model model, Authentication authentication) {
        model.addAttribute("model", expositionRepository.findAll());
        return "admin/expositions";
    }

    @GetMapping("/news")
    public String news(Model model, Authentication authentication) {
        model.addAttribute("model", newsRepository.findAll());
        return "admin/news";
    }

    @GetMapping("/compose")
    public String compose(Model model, Authentication authentication) {
        return "admin/compose";
    }

    @GetMapping("/mailbox")
    public String mailbox(Model model, Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);

        model.addAttribute("model", privateMessageRepository.findAllByRecipient(user));
        return "admin/mailbox";
    }

    @GetMapping("/mailbox/new")
    public String mailboxNew(Model model, Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);
        model.addAttribute("model", privateMessageRepository.findAllByRecipientAndIsRead(user, false));
        return "admin/mailbox";
    }

    @GetMapping("/mailbox/sent")
    public String mailboxSent(Model model, Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);
        model.addAttribute("model", privateMessageRepository.findAllBySender(user));
        return "admin/mailbox";
    }

    @GetMapping("/readMail/{id}")
    public String readMail(Model model, @PathVariable Long id, Authentication authentication) {
        PrivateMessage message = privateMessageRepository.getOne(id);
        message.setIsRead(true);
        privateMessageRepository.save(message);
        model.addAttribute("model", message);
        return "admin/read-mail";
    }

    @GetMapping("/composeTicket")
    public String composeTicket(Model model, Authentication authentication) {
        return "admin/composeTicket";
    }

    @GetMapping("/ticketbox/{status}")
    public String getTickets(Model model, @PathVariable String status) {
        List<Ticket> tickets = ticketRepository.findAllByTicketStatus(TicketStatus.valueOf(status.toUpperCase()));
        model.addAttribute("model", tickets);
        System.out.println(tickets);
        return "admin/ticketbox";
    }

    @GetMapping("/readTicket/{id}")
    public String readTicket(Model model, @PathVariable Long id, Authentication authentication) {
        Ticket ticket = ticketRepository.getOne(id);
        ticket.setTicketStatus(TicketStatus.PROCESSING);
        ticketRepository.save(ticket);
        model.addAttribute("model", ticket);
        return "admin/read-ticket";
    }
}
