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

    /**
     * this method removes a specified pos from linkedlist
     * @param pos
     * @return
     */
    public String remove(int pos){
        // Validation
        if(isEmpty() || pos < 0 || pos >= numElements){
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (numElements-1) + " inclusive. " +
                    "(Supplied" +
                    " index was" +
                    " " + pos+")");
        }

        String removed = null;
        // Remove from start of list
        // If position to remove from is 0 (i.e. start of list)
        if(pos == 0){
            // Snip off/jump over first element in list
            removed = first.data;
            first = first.next;
            // If the list is now empty (i.e. there was only one element in the list before we did the remove)
            if(first == null){
                // Wipe last node too
                last = null;
            }
        }else {
            // Create current node to track our current position in list (Tomasz in class example)
            Node current = first;
            // Create prev node to track the position of the node before us (Bema in class example)
            Node prev = null;

            // Loop up to position from which we should remove
            for(int i = 0; i < pos; i++){
                prev = current;
                current = current.next;
            }
            removed = current.data;
            // "Snip" node to be removed from list
            // This will make the node before us (Bema) point to the node after us (Oscar),
            // essentially routing the list around us (Tomasz) and ignoring us entirely
            prev.next = current.next;
            // Remove our link to the list so we know we're not in it anymore
            current.next = null;
            // If there is nothing after prev (i.e. we've reached the end of the list)
            if(prev.next == null){
                // Call this element the new end of the list
                last = prev;
            }
        }
        // Decrease the number of elements in the list as one has been removed
        numElements--;

        return removed;
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
