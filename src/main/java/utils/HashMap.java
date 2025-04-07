package utils;

public class HashMap {
    //Class variable
    private static final int INITIAL_SIZE = 10;

    //Instance attributes
    private int count;
    private LinkedList[] slotLists;

    //Constructors
    public HashMap() {
        this.slotLists = new LinkedList[INITIAL_SIZE];
        this.count = 0;
    }
}
