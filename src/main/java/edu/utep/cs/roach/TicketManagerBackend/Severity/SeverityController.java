package edu.utep.cs.roach.TicketManagerBackend.Severity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/severities")
public class SeverityController {

    @Autowired
    private SeverityRepository severityRepository;

    @GetMapping()
    public @ResponseBody
    Iterable<Severity> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) boolean in_use) {
        // TODO - use RequestParameters for search/filtering.
        return severityRepository.findAll();
    }
    @PreAuthorize("hasRole('TICKET_ADMIN')")
    @PostMapping()
    public @ResponseBody Severity add(@RequestBody Severity severity) {
        return severityRepository.save(severity);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Severity get(@PathVariable Integer id) {
        return severityRepository.findById(id).get();
    }

    @PreAuthorize("hasRole('TICKET_ADMIN')")
    @PutMapping(path = "/{id}")
    public @ResponseBody Severity update(@PathVariable Integer id, @RequestBody Severity user){
        // TODO - use id?
        return severityRepository.save(user);
    }

    @PreAuthorize("hasRole('TICKET_ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id){
        Severity severity = severityRepository.findById(id).get();
        severity.setIn_use(false);
        severityRepository.save(severity);
    }

}
