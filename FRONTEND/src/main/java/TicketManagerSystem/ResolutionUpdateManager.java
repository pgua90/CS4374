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

public class ResolutionUpdateManager {
    private String API_HOME;
    private RestTemplate restTemplate;
    private Cookie sessionCookie;

    public ResolutionUpdateManager(String API_HOME, Cookie sessionCookie) {
        this.sessionCookie = sessionCookie;
        this.API_HOME = API_HOME;
        restTemplate = new RestTemplate();
    }

    public List<ResolutionUpdate> getAll(){
        HttpHeaders headers = getHeadersWithCookies();
        return restTemplate.exchange(
                API_HOME + "/resolutionupdates",
                HttpMethod.GET, new HttpEntity<String>(headers),
                new ParameterizedTypeReference<List<ResolutionUpdate>>(){})
                .getBody();
    }

    public ResolutionUpdate add(ResolutionUpdate ResolutionUpdate) throws JsonProcessingException {
        HttpHeaders headers = getHeadersWithCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ResolutionUpdate);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        return restTemplate.postForObject(API_HOME + "/resolutionupdates", request, ResolutionUpdate.class);
    }

    public ResolutionUpdate get(int id){
        HttpHeaders headers = getHeadersWithCookies();
        HttpEntity<String> request = new HttpEntity<String>(headers);
        return restTemplate.exchange(
                API_HOME + "/resolutionupdates/" + id,
                HttpMethod.GET, new HttpEntity<String>(headers), ResolutionUpdate.class)
                .getBody();
    }

    public ResolutionUpdate update(ResolutionUpdate ResolutionUpdate) throws JsonProcessingException {
        HttpHeaders headers = getHeadersWithCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ResolutionUpdate);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        return restTemplate.exchange(
                API_HOME + "/resolutionupdates/" + ResolutionUpdate.getId(),
                HttpMethod.PUT, request, ResolutionUpdate.class)
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
