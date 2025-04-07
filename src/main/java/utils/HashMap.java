package utils;

/**
 * An implementation of a HashMap structure.
 * 
 * @author Cal Woods
 */
public class HashMap {
    //Class variable
    private static final int INITIAL_SIZE = 10;

    //Instance attributes
    private int count;
    private LinkedList[] slotLists;

    //Constructors
    /**
    * A no-argument constructor that initialises instance to default empty HashMap.
    */
    public HashMap() {
        this.slotLists = new LinkedList[INITIAL_SIZE];
        this.count = 0;
    }
}
