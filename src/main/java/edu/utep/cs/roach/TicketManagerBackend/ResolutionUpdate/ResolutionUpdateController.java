package edu.utep.cs.roach.TicketManagerBackend.ResolutionUpdate;

import edu.utep.cs.roach.TicketManagerBackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/resolutionupdates")
public class ResolutionUpdateController {

    @Autowired
    private ResolutionUpdateRepository resolutionUpdateRepository;

    @GetMapping()
    public @ResponseBody
    Iterable<ResolutionUpdate> getAll(
            @RequestParam(required = false) User user,
            @RequestParam(required = false) String timeSpent,
            @RequestParam(required = false) Integer ticketId) {

        // TODO - use RequestParameters for search/filtering.
        return resolutionUpdateRepository.findAll();
    }

    @PostMapping()
    public @ResponseBody ResolutionUpdate add(@RequestBody ResolutionUpdate resolutionUpdate) {
        return resolutionUpdateRepository.save(resolutionUpdate);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody ResolutionUpdate get(@PathVariable Integer id) {
        return resolutionUpdateRepository.findById(id).get();
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody ResolutionUpdate update(@PathVariable Integer id, @RequestBody ResolutionUpdate resolutionUpdate){
        // TODO - use id?
        return resolutionUpdateRepository.save(resolutionUpdate);
    }


}
