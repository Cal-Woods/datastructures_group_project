package utils;

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
    public Entry get(int pos) {
        //Validation
        if(pos < 0) {
            throw new IndexOutOfBoundsException("Given pos was "+pos+" which is an invalid place in a list, positive numbers only!");
        }
        if(pos >= this.numElements) {
            throw new IndexOutOfBoundsException("Given pos was "+pos+" but there is ONLY "+this.numElements+" elements in this list!");
        }

        return this.array[pos];
    }

    /**
     * Searches a dynamicArray instance for a given Entry object and gives the index of that object.
     * 
     * @param entry Given Entry object
     * 
     * @return The index of the Entry if found, -1 if the Entry was NOT found or if dynamicArray instance is empty
     * 
     * @throws NullPointerException If given User object was null
     */
    public int indexOf(Entry entry) {
        //Validation
        if(entry == null) {
            throw new NullPointerException("Given User object was null!");
        }
        if(this.isEmpty()) {
            System.out.println("This dynamicArray instance does NOT contain any elements.");

            return -1;
        }

        //Initialise for loop to iterate until number of elements
        for (int i = 0; i <  this.numElements; i++) {
            //Check the incoming User for every User in array
            if(entry.equals(this.array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Adds a given User to the end of the list.
     * @param user Given User
     * 
     * @throws NullPointerException If given User is null
     */
    public void add(Entry entry) {
        //Validation
        if(entry == null) {
            throw new NullPointerException("Given Entry to add was null!");
        }

        //If list numElements equals array.length
        if(this.numElements == this.array.length) {
            this.array = grow();
        }

        //Add to end of list
        this.array[this.numElements] = entry;

        //Increment count by 1
        this.numElements++;
    }


    public Entry remove(int pos) {
        //Validation
        validPos(pos);

        if(pos == this.numElements-1) {
            this.array[this.numElements-1] = null;
        }

        //Shift delete element at given pos
        Entry entry = shiftDelete(pos);

        this.numElements--;

        return entry;
    }

    //Private helper methods
    /**
     * Grows the DynamicArray instance array by 10
     * @return temp grown array
     */
    private Entry[] grow() {
        //Declare temp Entry array
        Entry[] temp = new Entry[this.numElements + STARTING_SIZE];

        //Copy all Entry objects from this.array to temp
        System.arraycopy(this.array, 0, temp, 0, this.numElements);

        //Return temp
        return temp;
    }

    /**
     * Checks if a given position is valid in the DynamicArray instance.
     * 
     * @param pos Given position
     * 
     * @return true if given position is valid.
     * 
     * @throws IndexOutOfBoundsException If given position is < 0 or given position >= number of elements in list
     */
    private boolean validPos(int pos) {
        if(pos < 0) {
            throw new IndexOutOfBoundsException("Given pos was "+pos+" which is an invalid position in a list or array!");
        }
        if(pos >= this.numElements) {
            throw new IndexOutOfBoundsException("Given pos was "+pos+" which is greater than the size of this list!");
        }

        return true;
    }

    /**
     * Uses the shift deletion method on a given position.
     * @param pos Given position
     * @return Deleted Entry object
     * @see Usage Do NOT use if given position is at the end of the DynamicArray
     */
    private Entry shiftDelete(int pos) {
        //Validation
        validPos(pos);

        //Declare Entry entry to store old Entry
        Entry entry = this.array[pos];

        //Initialise for loop
        for (int i = pos+1; i < this.numElements; i++) {
            this.array[i-1] = this.array[i];
        }

        //Set this.numElements - 1 = null
        this.array[this.numElements-1] = null;

        return entry;

    }
}