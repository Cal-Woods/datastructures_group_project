package business;

import utils.LinkedList;

public class Agent extends User {
    private int agentID;
    private String agentName;
    private LinkedList openTickets;
    private LinkedList closedTickets;

    /**
     * Constructs an Agent with the provided credentials and details.
     *
     * @param username   the username of the agent (must not be null)
     * @param password   the password of the agent (must not be null)
     * @param agentID    the unique ID of the agent (must be >= 0)
     * @param agentName  the full name of the agent (must not be null)
     * @throws IllegalArgumentException if any parameter is null or agentID is negative
     */
    public Agent(String username, String password, int agentID, String agentName) {
        super(username, password);
        this.agentID = agentID;
        this.agentName = agentName;
        this.openTickets = new LinkedList();
        this.closedTickets = new LinkedList();
    }

    /**
     * Gets the agent's ID.
     *
     * @return the agent's unique identifier
     */
    public int getAgentID() {
        return agentID;
    }

    /**
     * Sets the agent's ID.
     *
     * @param agentID the new agent ID (must be >= 0)
     * @throws IllegalArgumentException if agentID is negative
     */
    public void setAgentID(int agentID) {
        if (agentID < 0) {
            throw new IllegalArgumentException("Agent ID must be non-negative.");
        }
        this.agentID = agentID;
    }

    /**
     * Gets the agent's name.
     *
     * @return the name of the agent
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * Sets the agent's name.
     *
     * @param agentName the new agent name (must not be null)
     * @throws IllegalArgumentException if agentName is null
     */
    public void setAgentName(String agentName) {
        if (agentName == null) {
            throw new IllegalArgumentException("Agent name cannot be null.");
        }
        this.agentName = agentName;
    }

    /**
     * Gets the list of open tickets assigned to the agent.
     *
     * @return the list of open tickets
     */
    public LinkedList getOpenTickets() {
        return openTickets;
    }

    /**
     * Gets the list of closed tickets assigned to the agent.
     *
     * @return the list of closed tickets
     */
    public LinkedList getClosedTickets() {
        return closedTickets;
    }
}



