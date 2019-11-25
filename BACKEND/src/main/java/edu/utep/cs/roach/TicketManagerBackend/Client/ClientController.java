package edu.utep.cs.roach.TicketManagerBackend.Client;

import Utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
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
    Iterable<Client> getAll(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String number) {
        // TODO - use RequestParameters for search/filtering.

        Iterable<Client> clients = clientRepository.findAll();

        if(Utilities.areAllNull(firstName, lastName, email, number)){
            return clients;
        }

        HashSet<Client> clientSet = new HashSet<>();
        for(Client client: clients){
            clientSet.add(client);
        }

        if(firstName!= null){
            for(Client client: clients){
                if(!firstName.equals(client.getFirstName())){
                    if(clientSet.contains(client))
                        clientSet.remove(client);
                }
            }
        }

        if(lastName!= null){
            for(Client client: clients){
                if(!lastName.equals(client.getLastName())){
                    if(clientSet.contains(client))
                        clientSet.remove(client);
                }
            }
        }

        if(email!= null){
            for(Client client: clients){
                if(!email.equals(client.getEmail())){
                    if(clientSet.contains(client))
                        clientSet.remove(client);
                }
            }
        }
        //TODO Implement number
//	if(number!= null){
//		for(Client client: clients){
//			if(!number.equals(client.getNumber())){
//				if(clientSet.contains(client))
//					clientSet.remove(client);
//			}
//		}
//	}

        return clientSet;
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
