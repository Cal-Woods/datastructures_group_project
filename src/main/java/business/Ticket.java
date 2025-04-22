package business;

import java.time.LocalDateTime;
import java.util.Objects;


public class Ticket {
    private int ticketId;
    private String description;
    private int priority;
    private LocalDateTime createdAt;
    private String reportingUsername;
    private int allocatedAgentId; // nullable
    private String status;


    /**
     * define status of the ticket*/
    public enum status  {
        PENDING,
        IN_PROGRESS,
        STALLED,
        SOLVED
    }
    /**
     * Constructor to get a new ticket
     * @param ticketId ID of the ticket
     * @param description Descreption of the ticket
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

    public int getTicketId() {
        return ticketId;
    }
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        if(description == null || description.isBlank()){
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        this.description = description;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        if(priority < 1 || priority > 5){
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
        this.priority = priority;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getReportingUsername() {
        return reportingUsername;
    }
    public void setReportingUsername(String reportingUsername) {
        if(reportingUsername == null || reportingUsername.isBlank()){
            throw new IllegalArgumentException("ReportingUsername cannot be null or blank");
        }
        this.reportingUsername = reportingUsername;
    }
    public int getAllocatedAgentId() {
        return allocatedAgentId;
    }
    public void setAllocatedAgentId(int allocatedAgentId) {
        this.allocatedAgentId = allocatedAgentId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        if(status == null){
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
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

