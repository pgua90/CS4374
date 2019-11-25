package TicketManagerSystem;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Objects;

public class TicketManagerSystem {

    private static final TicketManagerSystem instance = new TicketManagerSystem();
    private Cookie sessionCookie;
    private String API_HOME = "http://localhost:8080";
    private RestTemplate restTemplate;
    private User user;
    private TicketManager ticketManager;
    private UserManager userManager;
    private SupportCategoryManager supportCategoryManager;
    private StatusManager statusManager;
    private SeverityManager severityManager;
    private ResolutionUpdateManager resolutionUpdateManager;
    private ClientManager clientManager;


    private TicketManagerSystem() {
        sessionCookie = new Cookie();
        restTemplate = new RestTemplate();
        ticketManager = new TicketManager(API_HOME, sessionCookie);
        userManager = new UserManager(API_HOME, sessionCookie);
        supportCategoryManager = new SupportCategoryManager(API_HOME, sessionCookie);
        statusManager = new StatusManager(API_HOME, sessionCookie);
        severityManager = new SeverityManager(API_HOME, sessionCookie);
        resolutionUpdateManager = new ResolutionUpdateManager(API_HOME, sessionCookie);
        clientManager = new ClientManager(API_HOME, sessionCookie);
    }

    public static TicketManagerSystem getInstance() {
        return instance;
    }

    public HttpStatus login(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_HOME + "/login")
                .queryParam("username", username)
                .queryParam("password", password);
        ResponseEntity<String> response = restTemplate.postForEntity( builder.toUriString(), request , String.class );
        if (response.getStatusCode() == HttpStatus.OK) {
            String cookie = response.getHeaders().getFirst("Set-Cookie");
            sessionCookie.setCookie(cookie);
            int userId = Integer.parseInt(Objects.requireNonNull(response.getHeaders().getFirst("User_Id")));
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Cookie", sessionCookie.getCookie());
            System.out.println(sessionCookie.getCookie());
            this.user = restTemplate.exchange(API_HOME + "/users/" + userId, HttpMethod.GET, new HttpEntity<String>(headers), User.class).getBody();
        }
        return response.getStatusCode();
    }

    public void logout() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", sessionCookie.getCookie());
        restTemplate.exchange(API_HOME + "/logout", HttpMethod.POST, new HttpEntity<String>(headers), Object.class);
        sessionCookie.setCookie("");
        this.user = null;
    }

    public User getUser() {
        return user;
    }

    public void updateUser() {
        this.user = restTemplate.getForEntity(API_HOME + "/users/" + user.getId(), User.class).getBody();
    }

    public TicketManager getTicketManager() {
        return ticketManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public SupportCategoryManager getSupportCategoryManager() {
        return supportCategoryManager;
    }

    public ResolutionUpdateManager getResolutionUpdateManager() {
        return resolutionUpdateManager;
    }

    public ClientManager getClientManager() {
        return clientManager;
    }

    public SeverityManager getSeverityManager() {
        return severityManager;
    }

    public StatusManager getStatusManager() {
        return statusManager;
    }
}
