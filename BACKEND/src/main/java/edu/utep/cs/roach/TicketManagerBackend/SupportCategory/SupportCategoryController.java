package edu.utep.cs.roach.TicketManagerBackend.SupportCategory;

import Utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/supportcategories")
public class SupportCategoryController {

    @Autowired
    private SupportCategoryRepository supportCategoryRepository;

    @GetMapping() public @ResponseBody Iterable<SupportCategory> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) boolean in_use) {
        // TODO - use RequestParameters for search/filtering.

        Iterable<SupportCategory> supports = supportCategoryRepository.findAll();

        if (Utilities.areAllNull(name, in_use)) {
            return supports;
        }

        HashSet<SupportCategory> supportSet = new HashSet<>();
        for (SupportCategory support : supports) {
            supportSet.add(support);
        }

        if (name != null) {
            for (SupportCategory support : supports) {
                if (!name.equals(support.getName())) {
                    if (supportSet.contains(support))
                        supportSet.remove(support);
                }
            }
        }
        /*
        if (in_use){
            for(SupportCategory support : supports){
                if(!name.equals(support.getName())){
                    if(supportSet.contains())
                        supportSet.remove(support);
                }
            }
        }
*/
        //TODO implement in_use at a later Date
//        if(in_use!=null){
//            for(SupportCategory support: supports){
//                if(in_use != support.getIn_Use()){
//                    if(supportSet.contains(support))
//                        supportSet.remove(support);
//                }
//            }
//        }

        return supportSet;
    }

    @PreAuthorize("hasRole('TICKET_ADMIN')")
    @PostMapping()
    public @ResponseBody SupportCategory add(@RequestBody SupportCategory supportCategory) {
        return supportCategoryRepository.save(supportCategory);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody SupportCategory get(@PathVariable Integer id) {
        return supportCategoryRepository.findById(id).get();
    }

    @PreAuthorize("hasRole('TICKET_ADMIN')")
    @PutMapping(path = "/{id}")
    public @ResponseBody SupportCategory update(@PathVariable Integer id, @RequestBody SupportCategory user){
        // TODO - use id?
        return supportCategoryRepository.save(user);
    }

    @PreAuthorize("hasRole('TICKET_ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id){
        SupportCategory supportCategory = supportCategoryRepository.findById(id).get();
        supportCategory.setIn_use(false);
        supportCategoryRepository.save(supportCategory);
    }

}
