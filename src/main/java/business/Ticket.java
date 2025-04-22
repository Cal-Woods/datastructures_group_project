package business;

import java.time.LocalDateTime;

/**
 * Represents a customer support ticket in the system.
 * each ticket should contain an ID, issue description,priority level
 * timestamp,reporting user,status
 */

public class Ticket {
    private int ticketId;
    private String description;
    private int priority;
    private LocalDateTime createdAt;
    private String reportingUsername;
    private int allocatedAgentId; // nullable
    private String status;


    /**
     * Constructor to get a new ticket
     * @param ticketId ID of the ticket
     * @param description Description of the ticket
     * @param priority priority level(1-5)
     * @param reportingUsername Username of the user
     * */

    public Ticket(int ticketId,String description,int priority,String reportingUsername){
        this.ticketId = ticketId;
        this.description = description;
        this.priority = priority;
        this.reportingUsername = reportingUsername;

        this.createdAt = LocalDateTime.now();
        this.allocatedAgentId = -1;
        this.status = "Pending";
    }

    //getters and setters
    /**
     * get ticket ID
     * @ return ticket ID
     */
    public int getTicketId() {
        return ticketId;
    }
    /**
     * set the ticketId
     * @param ticketId new ID for a ticket
     */
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    /**
     * get issue description
     * @return  the description of the ticket
     */
    public String getDescription() {
        return description;
    }
    /**
     * set the issue description
     * @param description new description of the issue
     * @throw illegalArgumentException if description is null or blank
     */
    public void setDescription(String description) {
        if(description == null || description.isBlank()){
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        this.description = description;
    }
    /**
     * get the priority level of the ticket
     * @return priority level which should be between(1-5)*/
    public int getPriority() {
        return priority;
    }
    /**
     * set priority level of the ticket
     * @param priority priority value from 1 to 5(1 being lowest and 5 being highest)
     * @throw illegalArgumentException if priority is not a level between 1 and 5
     */
    public void setPriority(int priority) {
        if(priority < 1 || priority > 5){
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
        this.priority = priority;
    }
    /**
     * get creation timestamp of the ticket
     * return date and time the ticket was created
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    /**
     * set creation timestamp of the ticket
     * @param createdAt the creation date and time to assign the ticket*/
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    /**
     * get username of the person who reported the ticket
     * @return reporting username
     */
    public String getReportingUsername() {
        return reportingUsername;
    }
    /**
     * set the reporting users username
     * @param reportingUsername the username of the reporter
     * throw illegalArgumentException if username is null or blank
     */
    public void setReportingUsername(String reportingUsername) {
        if(reportingUsername == null || reportingUsername.isBlank()){
            throw new IllegalArgumentException("ReportingUsername cannot be null or blank");
        }
        this.reportingUsername = reportingUsername;
    }
    /**
     * get the ID of agent assigned the ticket.
     * @return allocated agent ID or -1 if unassigned*/
    public int getAllocatedAgentId() {
        return allocatedAgentId;
    }
    /**
     * set the agent ID for the ticket
     * @param allocatedAgentId ID of assigned agent*/
    public void setAllocatedAgentId(int allocatedAgentId) {
        this.allocatedAgentId = allocatedAgentId;
    }
    /**
     * show current status of the ticket
     * */
    public String getStatus() {
        return status;
    }
    /**
     * status of the ticket
     * @param status A string representing status of a ticket
     * valid values on the tickets are "PENDING","IN PROGRESS","STALLED","SOLVED"
     * throw illegalArgument Exception if status null or empty*/

    public void setStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;

        switch (status.trim().toUpperCase()) {
            case "PENDING":
            case "IN PROGRESS":
            case "STALLED":
            case "SOLVED":
                this.status = status;
                break;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }
    }
    @Override
    public String toString() {
        return "Ticket ID: " + ticketId +
                "\nDescription: " + description +
                "\nPriority: " + priority +
                "\nCreated At: " + createdAt +
                "\nReported By: " + reportingUsername +
                "\nAgent ID: " + (allocatedAgentId == -1 ? "Unassigned" : allocatedAgentId) +
                "\nStatus: " + status;
    }
}

