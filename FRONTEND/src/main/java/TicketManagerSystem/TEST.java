package TicketManagerSystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

public class TEST {

    public static void main(String[] args) throws Exception{
        TicketManagerSystem system = TicketManagerSystem.getInstance();
        system.login("no", "no");
        System.out.println(system.getUser().getEmail());
        System.out.println(system.getUser().getRoles());
        System.out.println(system.getUser().getRoles().iterator().next().getRole());
        system.logout();
        system.login("Reagan", "password123456");
        System.out.println(system.getUser().getName());
        System.out.println(system.getUser().getRoles().iterator().next().getRole());


        ObjectMapper mapper = new ObjectMapper();
        List<User> users = system.getUserManager().getAll();
        for (User user : users){
            System.out.println(user.getName());
        }
//        system.getStatusManager().getAll().forEach((status) -> System.out.println(status.getName()));
//        Status status = new Status();
////        status.setId(3);
//        status.setName("Custom Status 4");
//        system.getStatusManager().add(status);
//        system.getStatusManager().getAll().forEach((status1) -> System.out.println(status1.getName()));
//        system.logout();
//        status.setName("Other name");
//        system.getStatusManager().update(status);
//        system.getStatusManager().getAll().forEach((status1) -> System.out.println(status1.getName()));


    }
}
