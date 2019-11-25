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

public class SupportCategoryManager {
    private String API_HOME;
    private RestTemplate restTemplate;
    private Cookie sessionCookie;

    public SupportCategoryManager(String API_HOME, Cookie sessionCookie) {
        this.sessionCookie = sessionCookie;
        this.API_HOME = API_HOME;
        restTemplate = new RestTemplate();
    }

    public List<SupportCategory> getAll(){
        HttpHeaders headers = getHeadersWithCookies();
        return restTemplate.exchange(
                API_HOME + "/supportcategories",
                HttpMethod.GET, new HttpEntity<String>(headers),
                new ParameterizedTypeReference<List<SupportCategory>>(){})
                .getBody();
    }

    public SupportCategory add(SupportCategory SupportCategory) throws JsonProcessingException {
        HttpHeaders headers = getHeadersWithCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(SupportCategory);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        return restTemplate.postForObject(API_HOME + "/supportcategories", request, SupportCategory.class);
    }

    public SupportCategory get(int id){
        HttpHeaders headers = getHeadersWithCookies();
        HttpEntity<String> request = new HttpEntity<String>(headers);
        return restTemplate.exchange(
                API_HOME + "/supportcategories/" + id,
                HttpMethod.GET, new HttpEntity<String>(headers), SupportCategory.class)
                .getBody();
    }

    public SupportCategory update(SupportCategory SupportCategory) throws JsonProcessingException {
        HttpHeaders headers = getHeadersWithCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(SupportCategory);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        return restTemplate.exchange(
                API_HOME + "/supportcategories/" + SupportCategory.getId(),
                HttpMethod.PUT, request, SupportCategory.class)
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
