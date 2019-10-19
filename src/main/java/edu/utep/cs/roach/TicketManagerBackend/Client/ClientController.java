package edu.utep.cs.roach.TicketManagerBackend.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping()
    public @ResponseBody
    Iterable<Client> getAll(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String number) {
        // TODO - use RequestParameters for search/filtering.
        return clientRepository.findAll();
    }

    @PostMapping()
    public @ResponseBody Client add(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Client get(@PathVariable Integer id) {
        return clientRepository.findById(id).get();
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody Client update(@PathVariable Integer id, @RequestBody Client client){
        // TODO - use id?
        return clientRepository.save(client);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id){
        Client client = clientRepository.findById(id).get();
        client.setActive(false);
        clientRepository.save(client);
    }

}
