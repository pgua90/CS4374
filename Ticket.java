import java.util.Date;

public class Ticket {
	private int id;
	private String status;
	private int priority;
	private String client;
	private String assignedTo;
	private Date open_Date;
	private Date close_Date;
	private String resolution;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Date getOpen_Date() {
		return open_Date;
	}

	public Date getClose_Date() {
		return close_Date;
	}

	public void setClose_Date(Date close_Date) {
		this.close_Date = close_Date;
	}

	public void setOpen_Date(Date open_DateAssigned) {
		this.open_Date = open_DateAssigned;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public Ticket() {
		
	}
	
	Ticket(int ticketID, String ticketStatus, int ticketPriority, String ticketClient, 
			String ticketAssignedTo,Date ticketOpenDate, Date ticketCloseDate, String ticketResolution) {
		id = ticketID;
		status = ticketStatus;
		priority = ticketPriority;
		client = ticketClient;
		assignedTo = ticketAssignedTo;
		open_Date = ticketOpenDate;
		close_Date = ticketCloseDate;
		resolution = ticketResolution;
	}
	
}
