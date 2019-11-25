package edu.utep.cs.roach.TicketManagerBackend.ResolutionUpdate;

import edu.utep.cs.roach.TicketManagerBackend.Ticket.Ticket;
import edu.utep.cs.roach.TicketManagerBackend.User.User;

import javax.persistence.*;

@Entity
public class ResolutionUpdate {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Ticket ticket;

    private String timeSpent;

    @ManyToOne
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public edu.utep.cs.roach.TicketManagerBackend.User.User getUser() {
        return user;
    }

    public void setUser(edu.utep.cs.roach.TicketManagerBackend.User.User user) {
        this.user = user;
    }
}
