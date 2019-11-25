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

public class SeverityManager {
    private String API_HOME;
    private RestTemplate restTemplate;
    private Cookie sessionCookie;

    public SeverityManager(String API_HOME, Cookie sessionCookie) {
        this.sessionCookie = sessionCookie;
        this.API_HOME = API_HOME;
        restTemplate = new RestTemplate();
    }

    public List<Severity> getAll(){
        HttpHeaders headers = getHeadersWithCookies();
        return restTemplate.exchange(
                API_HOME + "/severities",
                HttpMethod.GET, new HttpEntity<String>(headers),
                new ParameterizedTypeReference<List<Severity>>(){})
                .getBody();
    }

    public Severity add(Severity Severity) throws JsonProcessingException {
        HttpHeaders headers = getHeadersWithCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Severity);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        return restTemplate.postForObject(API_HOME + "/severities", request, Severity.class);
    }

    public Severity get(int id){
        HttpHeaders headers = getHeadersWithCookies();
        HttpEntity<String> request = new HttpEntity<String>(headers);
        return restTemplate.exchange(
                API_HOME + "/severities/" + id,
                HttpMethod.GET, new HttpEntity<String>(headers), Severity.class)
                .getBody();
    }

    public Severity update(Severity Severity) throws JsonProcessingException {
        HttpHeaders headers = getHeadersWithCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Severity);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        return restTemplate.exchange(
                API_HOME + "/severities/" + Severity.getId(),
                HttpMethod.PUT, request, Severity.class)
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
