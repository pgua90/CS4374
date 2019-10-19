package edu.utep.cs.roach.TicketManagerBackend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public @ResponseBody Iterable<User> getAll(
            @RequestParam(required = false) UserType type,
            @RequestParam(required = false) boolean isActive,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {
        // TODO - use RequestParameters for search/filtering.
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('TICKET_ADMIN')")
    @PostMapping()
    public @ResponseBody User add(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody User get(@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(path = "/{id}")
    public @ResponseBody User update(@PathVariable Integer id, @RequestBody User user){
        // TODO - use id?
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('TICKET_ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id){
        User user = userRepository.findById(id).get();
        user.setActive(0);
        userRepository.save(user);
    }

}
