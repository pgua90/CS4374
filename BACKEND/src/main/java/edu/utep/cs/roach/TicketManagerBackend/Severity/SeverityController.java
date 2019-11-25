package edu.utep.cs.roach.TicketManagerBackend.Severity;

import Utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/severities")
public class SeverityController {

    @Autowired
    private SeverityRepository severityRepository;
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
    public @ResponseBody Iterable<Severity> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer in_use) {
        // TODO - use RequestParameters for search/filtering.

        Iterable<Severity> severities = severityRepository.findAll();

        if(Utilities.areAllNull(name, in_use)){
            return severities;
        }
        HashSet<Severity> severitySet = new HashSet<>();
        for(Severity severity : severities){
            severitySet.add(severity);
        }

        if(name != null){
            for(Severity severity: severities){
                if(!name.equals(severity.getName())){
                    if(severitySet.contains(severity))
                        severitySet.remove(severity);
                }
            }
        }

        //TODO implement in_use check
//        if(in_use != true || in_use){
//            for(Severity severity: severities){
//                if(in_use != severity.isIn_use()){
//                    if(severitySet.contains(severity))
//                        severitySet.remove(severity);
//                }
//            }
//        }

        return severitySet;
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
