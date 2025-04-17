package business;

import java.util.LinkedList;

public class Agent extends User {
    int agentID;
    String agentName;
    private LinkedList<Ticket> openTickets;
    private LinkedList<Ticket> closedTickets;

    public int getAgentId() {
        return agentID;
    }
}
