package TicketManagerSystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import java.sql.*;
import TicketManagerSystem.Ticket;

import java.util.List;

public class TicketManager {
    private String API_HOME;
    private RestTemplate restTemplate;
    private Cookie sessionCookie;
    private Ticket ticket;

    public TicketManager(String API_HOME, Cookie sessionCookie) {
        this.sessionCookie = sessionCookie;
        this.API_HOME = API_HOME;
        restTemplate = new RestTemplate();
    }

    public List<Ticket> getAll(){
        HttpHeaders headers = getHeadersWithCookies();
        return restTemplate.exchange(
                API_HOME + "/tickets",
                HttpMethod.GET, new HttpEntity<String>(headers),
                new ParameterizedTypeReference<List<Ticket>>(){})
                .getBody();
    }

    //This is the method I added for the user of the adding of tickets. Was basing myself off of your code for this part.
    public void addTicket(String title, String description, String resolution, Status status, Severity severity, int priority, Client client, User assignedTo, Date openDate, Date closeDate, SupportCategory supportCategory){
        String sql = "INSERT INTO " +
                "ticket(title, description, resolution, status, severity, priority, client, assignedTo, openDate, closedDate, supportCategory)" +
                " VALUES (?,?,?,?,?,?,?,?,?);";
        //so for your application you put PreparedStatement preparedStatement = connection.preparedStatement(sql);
        //I couldn't get to this part, so I was trying to figure out how you'd go about it...
        /*
        try{
            /*
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, resolution);
            preparedStatement.setString(4, status);
            preparedStatement.setString(5, severity);
            preparedStatement.setString(6, priority);
            preparedStatement.setString(7, client);
            preparedStatement.setString(8, assignedTo);
            preparedStatement.setString(9, openDate);
            preparedStatement.setString(10, closedDate);
            preparedStatement.setString(11, supportCategory);
        } catch (SQLException e){
            showExceptionAlert(e);
        }
        */
    }

    public Ticket add(Ticket ticket) throws JsonProcessingException {
        HttpHeaders headers = getHeadersWithCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ticket);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        return restTemplate.postForObject(API_HOME + "/tickets", request, Ticket.class);
    }

    public Ticket get(int id){
        HttpHeaders headers = getHeadersWithCookies();
        HttpEntity<String> request = new HttpEntity<String>(headers);
        return restTemplate.exchange(
                API_HOME + "/tickets/" + id,
                HttpMethod.GET, new HttpEntity<String>(headers), Ticket.class)
                .getBody();
    }


//Added manually what fernie showed me he did for this one part.
    /*
    public List<Ticket> get(SupportCategory supportCateogry, Date closeDate, Date openDate, Severity severity, Status status, Priority priority, Client client, User user, Ticket ticket, Role role) {
        String sql = "http://localhost:8080/ticket?";
        String otherSQL = "";

        if (!(status == null)) {
            otherSQL = otherSQL + "status=" + status.toString();
        }
        if (!(priority == null)) {
        }
        if (!(client == null)) {
        }
        if (!(user == null)) {
        }
        if (!(ticket == null)) {
        }
        if (!(closeDate == null)) {
        }
        if (!(openDate == null)) {
        }
        if (!(supportCateogry == null)) {
        }
        if (!(role == null)) {
        }
        return null;
    }
*/
    public Ticket update(Ticket ticket) throws JsonProcessingException {
        HttpHeaders headers = getHeadersWithCookies();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ticket);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        return restTemplate.exchange(
                API_HOME + "/tickets/" + ticket.getId(),
                HttpMethod.PUT, request, Ticket.class)
                .getBody();
    }

    private HttpHeaders getHeadersWithCookies(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", sessionCookie.getCookie());
        return headers;
    }


}
