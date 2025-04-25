package utils;

import business.Ticket;

/**
 * A singly linked list to store Ticket objects in the system.
 * Supports basic operations such as add, get, remove, and size checking.
 */
public class LinkedList {
    private int numElements;
    private Node first;
    private Node last;

    /**
     * Constructs an empty LinkedList.
     */
    public LinkedList() {
        this.first = null;
        this.last = null;
        this.numElements = 0;
    }

    /**
     * Returns the number of tickets in the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return numElements;
    }

    /**
     * Adds a Ticket to the end of the list.
     *
     * @param ticket the Ticket to add
     * @throws IllegalArgumentException if the ticket is null
     */
    public void add(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Cannot add null ticket to list");
        }

        Node newNode = new Node(ticket);

        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }

        numElements++;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return numElements == 0;
    }

    /**
     * Retrieves the Ticket at a specific index.
     *
     * @param index the position of the ticket to retrieve
     * @return the Ticket at the specified index
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public Ticket get(int index) {
        if (isEmpty() || index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (numElements - 1) + ", supplied: " + index);
        }

        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    /**
     * Removes the Ticket at a specified index.
     *
     * @param pos the position of the ticket to remove
     * @return the removed Ticket
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Ticket remove(int pos) {
        if (isEmpty() || pos < 0 || pos >= numElements) {
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (numElements - 1) + ", supplied: " + pos);
        }

        Ticket removed;

        if (pos == 0) {
            removed = first.data;
            first = first.next;
            if (first == null) {
                last = null;
            }
        } else {
            Node current = first;
            Node prev = null;

            for (int i = 0; i < pos; i++) {
                prev = current;
                current = current.next;
            }

            removed = current.data;
            prev.next = current.next;
            current.next = null;

            if (prev.next == null) {
                last = prev;
            }
        }

        numElements--;
        return removed;
    }

    /**
     *  inner class representing a node in the linked list.
     */
    private static class Node {
        private Ticket data;
        private Node next;

        /**
         * Constructs a new Node with the given Ticket.
         *
         * @param data the Ticket to store in this node
         */
        public Node(Ticket data) {
            this.data = data;
            this.next = null;
        }
    }
}
