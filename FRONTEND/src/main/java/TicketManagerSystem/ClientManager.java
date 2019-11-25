package TicketManagerSystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ClientManager {
    private String API_HOME;
    private RestTemplate restTemplate;
    private Cookie sessionCookie;

    public ClientManager(String API_HOME, Cookie sessionCookie) {
        this.sessionCookie = sessionCookie;
        this.API_HOME = API_HOME;
        restTemplate = new RestTemplate();
    }

    public List<Client> getAll(){
        HttpHeaders headers = getHeadersWithCookies();
        return restTemplate.exchange(
                API_HOME + "/clients",
                HttpMethod.GET, new HttpEntity<String>(headers),
                new ParameterizedTypeReference<List<Client>>(){})
                .getBody();
    }

    public Client add(Client Client) throws JsonProcessingException {
        HttpHeaders headers = getHeadersWithCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Client);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        return restTemplate.postForObject(API_HOME + "/clients", request, Client.class);
    }

    public Client get(int id){
        HttpHeaders headers = getHeadersWithCookies();
        HttpEntity<String> request = new HttpEntity<String>(headers);
        return restTemplate.exchange(
                API_HOME + "/clients/" + id,
                HttpMethod.GET, new HttpEntity<String>(headers), Client.class)
                .getBody();
    }

    public Client update(Client Client) throws JsonProcessingException {
        HttpHeaders headers = getHeadersWithCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Client);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        return restTemplate.exchange(
                API_HOME + "/clients/" + Client.getId(),
                HttpMethod.PUT, request, Client.class)
                .getBody();
    }

    //TODO DELETE

    private HttpHeaders getHeadersWithCookies(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", sessionCookie.getCookie());
        return headers;
    }


}
