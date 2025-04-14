package utils;

public class LinkedList {
    private int numElements;

    private node first;
    private node last;

    /**
     * No-argument constructor initialising the list to default settings.
     */
    public LinkedList(){
        this.first = null;
        this.last = null;
        this.numElements = 0;
    }

    public int size(){
        return numElements;
    }






public static class node {
    private String data;
    private node next;

    public node(String data) {
        this.data = data;
        this.next = null;
    }
}

}
