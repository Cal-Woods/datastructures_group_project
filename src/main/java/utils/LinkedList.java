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
    /**
     * Returns true if this list contains no elements.
     * @return True if this list contains no elements, false otherwise
     */
    public boolean isEmpty(){
        return numElements == 0;
        // Could also use this condition:
        // return first == null;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= numElements) or the list
     * is empty
     */
    public String get(int index){
        // VALIDATION:
        if(isEmpty() || index < 0 || index >= numElements){
            throw new IndexOutOfBoundsException("Index must be between 0 and " + numElements + ". (Supplied index was" +
                    " " + index+")");
        }

        // SET UP:
        Node current = first;
        for(int i = 0; i < index; i++){
            current = current.next;
        }

        return current.data;
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
