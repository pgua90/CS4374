package edu.utep.cs.roach.TicketManagerBackend.Ticket;

import edu.utep.cs.roach.TicketManagerBackend.Client.ClientRepository;
import edu.utep.cs.roach.TicketManagerBackend.Severity.SeverityRepository;
import edu.utep.cs.roach.TicketManagerBackend.Status.StatusRepository;
import edu.utep.cs.roach.TicketManagerBackend.SupportCategory.SupportCategoryRepository;
import edu.utep.cs.roach.TicketManagerBackend.User.User;
import edu.utep.cs.roach.TicketManagerBackend.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping()
    public @ResponseBody Iterable<Ticket> getAllTickets(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String resolution,
            @RequestParam(required = false) Integer statusId,
            @RequestParam(required = false) Integer severityId,
            @RequestParam(required = false) Integer priority,
            @RequestParam(required = false) Integer clientId,
            @RequestParam(required = false) Integer assign_toId,
            @RequestParam(required = false) String openDate,
            @RequestParam(required = false) String closeDate,
            @RequestParam(required = false) Integer supportCategoryId) {
        // TODO - use RequestParameters for search/filtering.
        return ticketRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('TICKET_ADMIN', 'DEPARTMENT_SYSADMIN')")
    @PostMapping()
    public @ResponseBody Ticket add(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Ticket get(@PathVariable Integer id) {
        return ticketRepository.findById(id).get();
    }

    @PreAuthorize("hasAnyRole('TICKET_ADMIN', 'MANAGER','DEPARTMENT_SYSADMIN', 'STUDENT_SUPPORT')")
    @PutMapping(path = "/{id}")
    public @ResponseBody Ticket update(@PathVariable Integer id, @RequestBody Ticket ticket){
        // TODO - use id?
        return ticketRepository.save(ticket);
    }


}
