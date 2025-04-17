package business;

import utils.LinkedList;

public class Agent extends User {
    int agentID;
    String agentName;
    //data s for
    private LinkedList openTickets;
    private LinkedList closedTickets;

    /**
     * Constructor for agent
     * @param username
     * @param password
     * @param agentID
     * @param agentName
     */
    public Agent(String username, String password, int agentID, String agentName) {
        super(username, password);
        this.agentID = agentID;
        this.agentName = agentName;
        this.openTickets = new LinkedList();
        this.closedTickets = new LinkedList();
    }

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
