package edu.utep.cs.roach.TicketManagerBackend.Status;

import Utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/status")
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;
/*
    private boolean areAllNull(Object... fields){
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
    public @ResponseBody
    Iterable<Status> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) boolean in_use) {
        // TODO - use RequestParameters for search/filtering.

        Iterable<Status> allStatus = statusRepository.findAll();

        if(Utilities.areAllNull(name, in_use)){
            return allStatus;
        }

        HashSet<Status> statusSet = new HashSet<>();
        for(Status status: allStatus){
            statusSet.add(status);
        }

        if(name != null){
            for(Status status: allStatus){
                if(!name.equals(status.getName())){
                    if(statusSet.contains(status))
                        statusSet.remove(status);
                }
            }
        }

        //TODO implement in_use check
//        if(in_use!= null){
//            for(Status status: allStatus){
//                if(in_use != status.getIn_Use()){
//                    if(statusSet.contains(status))
//                        statusSet.remove(status);
//                }
//            }
//        }
        return statusSet;
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
