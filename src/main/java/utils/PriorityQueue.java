package utils;

import business.Ticket;

/**
 * A queue that inserts Tickets based on Ticket priority level.
 * 
 * @author group effort
 */
public class PriorityQueue {
    private int numElements;
    private LinkedList list;

    /**
     * Gets the first object in queue.
     * 
     * @return Retrieved Ticket
     * 
     * @throws IllegalStateException If queue is empty.
     * 
     * @author Cal Woods
     */
    // public Ticket peek() {
	// 	if (list.isEmpty() == true) {
	// 		throw new IllegalStateException("Queue contains 0 elements!");
	// 	}
	// 	return list.get(0);
	// }
    
    /**
     * Removes the front Ticket from the queue.
     * 
     * @author Cal Woods
     * 
     * @return The removed Ticket
     * 
     * @throws IllegalStateException If queue is empty.
     */
	// public Ticket dequeue() {
	// 	if (list.isEmpty() == true) {
	// 		throw new IllegalStateException("Queue contains 0 elements!");
	// 	}

	// 	//Remove element at front of list
	// 	Ticket returned = list.remove(0);
		
	// 	numElements--;
		
	// 	return returned;
	// }

    /**
     * Adds a given Ticket object to the queue at correct place based on Ticket priority.
     * 
     * @param ticket Given Ticket object
     * 
     * @return Nothing
     * 
     * @throws NullPointerException If given ticket was null.
     */
	// public void enqueue(Ticket ticket) {
	// 	if (ticket == null) {
	// 		throw new NullPointerException("Given ticket was null!");
	// 	}
	// 	if(list.isEmpty() == true) {
	// 		list.add(ticket);
			
	// 		numElements++;
			
	// 		return;
	// 	}
		
	// 	//If Ticket at front of list has a greater priority than given ticket, insert in the next slot
	// 	if (ticket.getPriority() < list.get(0).getPriority()) {
	// 		//Add to the start of the list
	// 		list.add(0, ticket);
	// 	}
	// 	//If Ticket at end of list has a lesser priority
	// 	else if (ticket.getPriority() > list.get(list.size() - 1).getPriority()) {
	// 		//Always add to end of list in this case
	// 		list.add(ticket);
	// 	} 
	// 	else {
	// 		//In this case, it will be an O(n) linear search for the correct insert position
	// 		for (int i = 0; i < list.size(); i++) {
	// 			if (ticket.getPriority() < list.get(i).getPriority()) {
	// 				list.add(i, ticket);
					
	// 				break;
	// 			}
	// 		}
	// 	}
	// 	numElements++;
	// }

    /**
     * check if queue is empty
     * the method should return empty if queue is empty
     * @return true if there is nothing; false if otherwise
     * */
    public boolean isEmpty() {
        return this.numElements == 0;
    }
    /**
     * check amount of elements in the queue
     * the method should return the number of elements in the queue
     * @return number of elements
     * */
    public int size() {
        return this.numElements;
    }

}
