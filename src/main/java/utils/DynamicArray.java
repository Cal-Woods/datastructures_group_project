package utils;
/**This is a dynamic arraylist that holds entry to objects**/
/**It initializes a DynamicArray instance with 0 numElements $ an Entry array of size STARTING_ SIZE==10**/
public class DynamicArray {
    private static final int STARTING_SIZE = 10;
    private int numElements;
    private Entry[] array;

    public DynamicArray() {
        this.numElements = 0;
        this.array = new Entry[STARTING_SIZE];
    }

    /**
     * Should get current number of elemnts on the list in the array
     * should return the number of elemnts on the list
     **/

    public int size() {
        return this.numElements;
    }

    /**
     * The method should check the list is empty
     * The method should return true if the list is empty
     * if not, the method should return false if the list is not empty
     **/
    public boolean isEmpty() {
        return this.numElements == 0;
    }

    /**
     * Get entry of an object from a specific position
     *
     * @param position specified position
     *                 return the specified position on the list
     *                 a validation will be used which will help us locate the object targeted on the list
     **/

    public Entry get(int position) {
        if (position < 0 || position >= this.numElements) {
            throw new IndexOutOfBoundsException();
        }
        return array[position];
    }


    /**
     * search through the list for a given object and give the index of the object being searched
     *
     * @user given user
     * @return the index of the user if found and if not, -1 if the user was not found or incase the list is empty
     **/

    public int indexOf(Entry entry) {
        if (entry == null) {
            throw new NullPointerException("entry is null");
        }
        for (int i = 0; i < this.numElements; i++) {
            if (array[i].equals(entry)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * add a new entry
     **/
    public void add(Entry entry) {
        if (entry == null) {
            throw new NullPointerException("list is null");
        }
        if (this.numElements == array.length) {
            array = grow();
        }
        array[numElements++] = entry;
    }

    /**
     * removing entry from a list
     *
     * @param entry the index of the item to remove from the list
     *              return removed item from the list
     *              a validation will be used to check if the list is empty
     **/

    public void remove(Entry entry) {
        if (entry == null) {
            throw new IndexOutOfBoundsException("list is null");
        }

        /*
        //Another implementation
        int index = indexOf(entry);

        if(array[index] == null) {
            throw new NullPointerException("Entry not found!");
        }

        if(index == numElements-1) {
            array[index] = null;

            return;
        }

        //Shift delete algorithm
        for(int i = index+1; i < numElements; i++) {
            array[i-1] = array[i];
        }*/

        for (int i = 0; i < this.numElements - 1; i++) {
            if (array[i] == entry) {
                array[i] = array[i + 1];
            }
        }
        array[numElements - 1] = null;

        numElements--;
    }

    //private helper method
    /** grows the dynamicarray instance array by 10
     * @return temp grown away */
    //declare temp Entry array
    private Entry[] grow(){
        Entry temp[] = new Entry[STARTING_SIZE];

        //copy all entry objects from this.array to temp
        System.arraycopy(array, 0, temp, 0, numElements);

        return temp;
    }
}
