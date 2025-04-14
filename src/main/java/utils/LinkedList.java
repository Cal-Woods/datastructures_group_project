package utils;

public class LinkedList {
    private int numElements;

    private Node first;
    private Node last;

    /**
     * No-argument constructor initialising the list to default settings.
     */
    public LinkedList(){
        this.first = null;
        this.last = null;
        this.numElements = 0;
    }

    /**
     * Size Method for linkedlist
     * @return
     */
    public int size(){
        return numElements;
    }

    /**
     * Add the specified element to the end of this list.
     * @param value the element to be added to this list
     * @throws IllegalArgumentException if the value to be added is null
     */
    public void add(String value){
        // VALIDATION
        if(value == null){
            throw new IllegalArgumentException("Null cannot be added to list");
        }
        // SET UP:
        // Wrap incoming data in node
        Node newNode = new Node(value);

        // LOGIC:
        // Deal with adding first element to list
        // If the list does not already have a value / is empty
        if(first == null){
            // Set the first element in the list to be the new node
            first = newNode;
            last = newNode;
        }else{
            last.next = newNode;
            last = newNode;
        }

        numElements++;
    }






public static class Node {
    private String data;
    private Node next;

    public Node(String data) {
        this.data = data;
        this.next = null;
    }
}

}
