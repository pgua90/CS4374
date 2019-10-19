package edu.utep.cs.roach.TicketManagerBackend.Ticket;

import edu.utep.cs.roach.TicketManagerBackend.Client.Client;
import edu.utep.cs.roach.TicketManagerBackend.Severity.Severity;
import edu.utep.cs.roach.TicketManagerBackend.Status.Status;
import edu.utep.cs.roach.TicketManagerBackend.SupportCategory.SupportCategory;
import edu.utep.cs.roach.TicketManagerBackend.User.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String title;

    private String description;

    private String resolution;

    @ManyToOne
    private Status status;

    @ManyToOne
    private Severity severity;

    private int priority;

    @ManyToOne
    private Client client;

    @ManyToOne
    private User assigned_to;

    private Date openDate;

    private Date closeDate;

    @ManyToOne
    private SupportCategory supportCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(User assigned_to) {
        this.assigned_to = assigned_to;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public SupportCategory getSupportCategory() {
        return supportCategory;
    }

    public void setSupportCategory(SupportCategory supportCategory) {
        this.supportCategory = supportCategory;
    }
}
