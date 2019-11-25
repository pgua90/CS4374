package edu.utep.cs.roach.TicketManagerBackend.ResolutionUpdate;

import Utilities.Utilities;
import edu.utep.cs.roach.TicketManagerBackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/resolutionupdates")
public class ResolutionUpdateController {

    @Autowired
    private ResolutionUpdateRepository resolutionUpdateRepository;
/*
    private boolean areAllNull(Object... fields) {
        boolean areAllNull = true;
        for (Object field : fields){
            if (field != null) {
                areAllNull = false;
                break;
            }
        }
        return areAllNull;
    }
*/
    @GetMapping()
    public @ResponseBody
    Iterable<ResolutionUpdate> getAll(
            @RequestParam(required = false) User user,
            @RequestParam(required = false) String timeSpent,
            @RequestParam(required = false) Integer ticketId) {

        // TODO - use RequestParameters for search/filtering.
        Iterable<ResolutionUpdate> resolutions = resolutionUpdateRepository.findAll();

        if(Utilities.areAllNull(user, timeSpent, ticketId)){
            return resolutions;
        }

        HashSet<ResolutionUpdate> resolutionSet = new HashSet<>();
        for(ResolutionUpdate resolution: resolutions){
            resolutionSet.add(resolution);
        }

        if(user != null){
            for(ResolutionUpdate resolution: resolutions){
                if(user.getName().equals(resolution.getUser().getName())){
                    if(resolutionSet.contains(resolution))
                        resolutionSet.remove(resolution);
                }
            }
        }

        if(timeSpent != null){
            for(ResolutionUpdate resolution: resolutions){
                if(!timeSpent.equals(resolution.getTimeSpent())){
                    if(resolutionSet.contains(resolution))
                        resolutionSet.remove(resolution);
                }
            }
        }

        if(ticketId!= null){
            for(ResolutionUpdate resolution: resolutions){
                if(ticketId != resolution.getId()){
                    if(resolutionSet.contains(resolution))
                        resolutionSet.remove(resolution);
                }
            }
        }

        return resolutionSet;
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
