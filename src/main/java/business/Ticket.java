package business;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;
    private String description;
    private int priority;
    private LocalDateTime createdAt;
    private String reportingUsername;
    private int allocatedAgentId; // nullable
    private String status;

}
