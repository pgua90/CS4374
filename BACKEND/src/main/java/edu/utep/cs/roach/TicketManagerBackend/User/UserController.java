package edu.utep.cs.roach.TicketManagerBackend.User;

import Utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
/*
    private boolean areAllNull(Object... fields) {
        System.out.println("in areAllNull");
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
    public @ResponseBody Iterable<User> getAll(
            @RequestParam(required = false) UserType type,
            @RequestParam(required = false) Integer isActive,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String username) {
        // TODO - use RequestParameters for search/filtering.
        Iterable<User> users = userRepository.findAll();


        System.out.println("before if areAllnull");
        if (Utilities.areAllNull(type, isActive, firstName, lastName, email, number, username)) {
            return users;
        }

        HashSet<User> userSet = new HashSet<>();

        for (User user : users) {
            userSet.add(user);
        }

//        List<User> foundUsers = new ArrayList<>();

        if (type != null) {
            for (User user : users) {
                if (!user.getRoles().iterator().next().getRole().equals(type.toString())) {
                    if (userSet.contains(user))
                        userSet.remove(user);
                }
            }
        }

        if (isActive != null) {
            for (User user : users) {
                if (user.getActive() != isActive) {
                    if (userSet.contains(user))
                        userSet.remove(user);
                }
            }
        }

        if (firstName != null) {
            for (User user : users) {
                if (!firstName.equals(user.getName())) {
                    if (userSet.contains(user))
                        userSet.remove(user);
                }
            }
        }

        if (lastName != null) {
            for (User user : users) {
                if (!lastName.equals(user.getLastName())) {
                    if (userSet.contains(user))
                        userSet.remove(user);
                }
            }
        }

        if (email != null) {
            System.out.println("USERSET: " + userSet);
            for (User user : users) {
                if (!email.equals(user.getEmail())) {
                    if (userSet.contains(user)) {
                        userSet.remove(user);
                    }
                }
            }
        }

        // TODO implement number
//        if (number != null) {
//            for (User user : userSet) {
//                if (!number.equals(user.g())) {
//                    userSet.remove(user);
//                }
//            }
//        }

        // TODO implement username
//        if (type != null) {
//            for (User user : userSet) {
//                if (!user.getRoles().iterator().next().getRole().equals(type)) {
//                    userSet.remove(user);
//                }
//            }
//        }

        return userSet;
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
