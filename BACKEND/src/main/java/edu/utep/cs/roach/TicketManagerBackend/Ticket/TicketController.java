package edu.utep.cs.roach.TicketManagerBackend.Ticket;

import Utilities.Utilities;
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
import java.util.Date;
import java.util.HashSet;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/tickets")

public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;
/*
    private boolean areAllNull(Object... fields){
        System.out.println("in areAllNull");
        boolean areAllNull = true;
        for(Object field : fields){
            if(field != null){
                areAllNull = false;
                break;
            }
        }
        return areAllNull;
    }
*/
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
            @RequestParam(required = false) Date openDate,
            @RequestParam(required = false) Date closeDate,
            @RequestParam(required = false) Integer supportCategoryId) {
        // TODO - use RequestParameters for search/filtering.
        Iterable<Ticket> tickets = ticketRepository.findAll();
        //START
        if(Utilities.areAllNull(title, description, resolution, statusId, severityId, priority, clientId, assign_toId, openDate, closeDate, supportCategoryId)){
            return tickets;
        }

        HashSet<Ticket> ticketSet = new HashSet<>();

        for (Ticket ticket : tickets){
            ticketSet.add(ticket);
        }

        if (title != null){
            for(Ticket ticket : tickets){
                if(!title.equals(ticket.getTitle())){
                    if(ticketSet.contains(ticket))
                        ticketSet.remove(ticket);
                }
            }
        }

        if (description != null){
            for(Ticket ticket : tickets){
                if(!description.equals(ticket.getDescription())){
                    if(ticketSet.contains(ticket))
                        ticketSet.remove(ticket);
                }
            }
        }

        if(resolution != null){
            for(Ticket ticket : tickets){
                if(!resolution.equals(ticket.getResolution())){
                    if(ticketSet.contains(ticket))
                        ticketSet.remove(ticket);
                }
            }
        }

        if(statusId != null){
            for(Ticket ticket : ticketSet){
                if(!statusId.equals(ticket.getStatus().getId())){
                    ticketSet.remove(ticket);
                }
            }
        }

        if(severityId != null){
            for(Ticket ticket : ticketSet){
                if(!severityId.equals(ticket.getSeverity().getId())){
                    ticketSet.remove(ticket);
                }
            }
        }

        if(priority != null){
            for(Ticket ticket : ticketSet){
                if(!priority.equals(ticket.getPriority())){
                    if(ticketSet.contains(ticket)){
                        ticketSet.remove(ticket);
                    }
                }
            }
        }
        System.out.println("clientid: next");
        if(clientId != null){
            System.out.println("in if exist");
            for(Ticket ticket : ticketSet){
                if(!clientId.equals(ticket.getClient().getId())){
                    System.out.println("if not client id");
                    if(ticketSet.contains(ticket))
                        ticketSet.remove(ticket);
                }
            }
        }

        if(assign_toId != null){
            for(Ticket ticket : tickets){
                if(!assign_toId.equals(ticket.getAssigned_to().getId())){
                    if(ticketSet.contains(ticket))
                        ticketSet.remove(ticket);
                }
            }
        }
//Find a way to set it to day day month month year year formatting...
        if(openDate != null){
            for(Ticket ticket : tickets){
                if(!openDate.equals(ticket.getOpenDate())){
                    if(ticketSet.contains(ticket))
                        ticketSet.remove(ticket);
                }
            }
        }

        if(closeDate != null){
            for(Ticket ticket : tickets){
                if(!closeDate.equals(ticket.getCloseDate())){
                    if(ticketSet.contains(ticket))
                        ticketSet.remove(ticket);
                }
            }
        }
//        return ticketRepository.findAll();
        return ticketSet;
    }

    @PreAuthorize("hasAnyRole('TICKET_ADMIN', 'MANAGER','DEPARTMENT_SYSADMIN', 'STUDENT_SUPPORT')")
    @PostMapping()
    public @ResponseBody Ticket add(@RequestBody Ticket ticket) {

        System.out.println("TICKET ____");
        System.out.println(ticket);
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
