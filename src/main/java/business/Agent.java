package business;

import java.util.LinkedList;

public class Agent extends User {
    int agentID;
    String agentName;
    //data s for
    private LinkedList<Ticket> openTickets;
    private LinkedList<Ticket> closedTickets;

}
