package utils;

import business.User;
import utils.Entry;

/**
 * A dynamic ArrayList to hold Entry objects
 */
public class DynamicArray {
    private int numElements;
    private Entry[] array;
    
    private static final int STARTING_SIZE = 10;

    //Constructors
    /**
     *Initialises a dynamicArray instance with 0 numElements & an Entry array of size STARTING_SIZE == 10
     */
    public DynamicArray() {
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

    /**
     * Searches a dynamicArray instance for a given User object and gives the index of that object.
     * 
     * @param user Given User
     * 
     * @return The index of the User if found, -1 if the User was NOT found or if dynamicArray instance is empty
     * 
     * @throws NullPointerException If given User object was null
     */
    public int indexOf(User user) {
        //Validation
        if(user == null) {
            throw new NullPointerException("Given User object was null!");
        }
        if(this.isEmpty()) {
            System.out.println("This dynamicArray instance does NOT contain any elements.");

            return -1;
        }

        //Initialise for loop to iterate until number of elements
        for (int i = 0; i <  this.numElements; i++) {
            //Check the incoming User for every User in array
            if(user.equals(this.array[i].getValue())) {
                return i;
            }
        }

        return -1;
    }


    public void add(User user) {
        //Validation
        if(user == null) {
            throw new NullPointerException("Given User to add was null!");
        }

        //If list numElements equals array.length
        if(this.numElements == array.length) {
            //TODO: grow()
        }
        //Add to end of list
        this.array[this.numElements] = new Entry(user.getUsername(), user);
    }
}