package utils;


import business.User;

public class LinkedList {
    private int numElements;
    private Node first;
    private Node last;

    /**
     * No-argument constructor initializing the list to default settings.
     */
    public LinkedList() {
        this.first = null;
        this.last = null;
        this.numElements = 0;
    }

    /**
     * Returns the number of users in the list.
     */
    public int size() {
        return numElements;
    }

    /**
     * Adds a user to the end of this list.
     * @param user the User to be added to the list
     * @throws IllegalArgumentException if the user is null
     */
    public void add(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot add null user to list");
        }

        Node newNode = new Node(user);

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
     * Returns true if this list contains no users.
     */
    public boolean isEmpty() {
        return numElements == 0;
    }

    /**
     * Returns the user at the specified position in this list.
     * @param index position of the user to retrieve
     * @return the User at the specified index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public User get(int index) {
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
     * Removes the user at the specified position from the list.
     * @param pos position of the user to remove
     * @return the removed User
     */
    public User remove(int pos) {
        if (isEmpty() || pos < 0 || pos >= numElements) {
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (numElements - 1) + ", supplied: " + pos);
        }

        User removed;

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
     * Internal Node class to hold User objects.
     */
    public static class Node {
        private User data;
        private Node next;

        public Node(User data) {
            this.data = data;
            this.next = null;
        }
    }
}
