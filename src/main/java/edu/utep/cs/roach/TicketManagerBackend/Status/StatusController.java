package edu.utep.cs.roach.TicketManagerBackend.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/status")
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @GetMapping()
    public @ResponseBody
    Iterable<Status> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) boolean in_use) {
        // TODO - use RequestParameters for search/filtering.
        return statusRepository.findAll();
    }

    @PreAuthorize("hasRole('TICKET_ADMIN')")
    @PostMapping()
    public @ResponseBody Status add(@RequestBody Status status) {
        return statusRepository.save(status);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Status get(@PathVariable Integer id) {
        return statusRepository.findById(id).get();
    }

    @PreAuthorize("hasRole('TICKET_ADMIN')")
    @PutMapping(path = "/{id}")
    public @ResponseBody Status update(@PathVariable Integer id, @RequestBody Status status){
        // TODO - use id?
        return statusRepository.save(status);
    }

    @PreAuthorize("hasRole('TICKET_ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id){
        Status status = statusRepository.findById(id).get();
        status.setIn_use(false);
        statusRepository.save(status);
    }

}
