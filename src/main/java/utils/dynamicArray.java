package utils;

import business.User;
import utils.Entry;

/**
 * A dynamic ArrayList to hold Entry objects
 */
public class dynamicArray {
    private int numElements;
    private Entry[] array;
    
    private static final int STARTING_SIZE = 10;

    //Constructors
    /**
     *Initialises a dynamicArray instance with 0 numElements & an Entry array of size STARTING_SIZE == 10
     */
    public dynamicArray() {
        this.numElements = 0;
        this.array = new Entry[STARTING_SIZE];
    }

    /**
     * Gets current number of elements in dynamicArray instance.
     * 
     * @return Number of elements in instance
     */
    public int size() {
        return this.numElements;
    }

    /**
     * Gets whether or not dynamicArray instance is empty.
     * 
     * @return True if dynamicArray instance contains 0 elements, false otherwise
     */
    public boolean isEmpty() {
        return this.numElements == 0;
    }
    
    /**
     * Gets an Entry object at a given position.
     * @param pos Given position
     * 
     * @return An Entry object
     */
    public User get(int pos) {
        //Validation
        if(pos < 0) {
            throw new IndexOutOfBoundsException("Given pos was "+pos+" which is an invalid place in a list, positive numbers only!");
        }
        if(pos >= this.numElements) {
            throw new IndexOutOfBoundsException("Given pos was "+pos+" but there is ONLY "+this.numElements+" elements in this list!");
        }

        return this.array[pos].getValue();
    }
}