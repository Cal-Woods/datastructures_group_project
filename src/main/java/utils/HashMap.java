package utils;

import business.User;

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
    private DynamicArray[] slotLists;

    //Constructors
    /**
    * A no-argument constructor that initialises instance to default empty HashMap.
    */
    public HashMap() {
        this.slotLists = new DynamicArray[INITIAL_SIZE];
        this.count = 0;
    }

    /**
     * Puts a given User entry into HashMap instance if the key is unique.
     * @param value Given User
     * @return Added User object or null if key is already present in HashMap.
     * 
     * @throws NullPointerException If given User is null
     */
    public User put(String key, User value) {
        //Validation
        if(value == null) {
            throw new NullPointerException("Given User object is null which is NOT allowed!");
        }
        if(key == null) {
            throw new NullPointerException("Given key was null which bis NOT allowed!");
        }
        if(key.isBlank()) {
            throw new IllegalArgumentException("Given key was "+key+" which is empty or full of whitespace, NOT allowed!");
        }

        //Declare int calculated to store calculated slot
        int calculated = hashFunction(key);

        //Check if calculated slot is empty
        if(slotLists[calculated] == null) {
            //Initialise slot
            slotLists[calculated] = new DynamicArray();
        }

        if(containsKey(key)) {
            System.err.println("Incoming key value is already present in slot list of HashMap instance.");

            return null; 
        }

        //Add Entry objects to slotLists at calculated index
        slotLists[calculated].add(new Entry(key, value));

        this.count++;
        return value;
    }

    public User get(String key) {
        //Validation
        if(key == null) {
            throw new NullPointerException("Given key was null!");
        }
        if(key.isBlank()) {
            throw new IllegalArgumentException("Given key was "+key+" which is blank and NOT allowed!");
        }

        //Declare int index set to calcSlot() method
        int index = this.hashFunction(key);

        //Check if slotLists[index] is null
        if(this.slotLists[index] == null) {
            return null;
        }

        //Initialise for loop to iterate through this.slotLists to find matching key
        for (int i = 0; i < this.slotLists[index].size(); i++) {
            //Check if given key matches Entry key
            if(key.equals(this.slotLists[index].get(i).getKey())) {
                return this.slotLists[index].get(i).getValue();
            }
        }


        return null;
    }
    /**
     * Gets the current count of elements in HashMap instance.
     * 
     * @return The count of elements
     */
    public int getCount() {
        return count;
    }

    public User remove(String key) {
        if(key == null) {
            throw new NullPointerException("Given key was null!");
        }
        if(key.isBlank()) {
            throw new IllegalArgumentException("Given key was ["+key+"], which is either empty or full of whitespace!");
        }

        //Store key hash
        int hash = hashFunction(key);

        //Store index of DynamicArray slot
        int index = this.slotLists[hash].indexOf(new Entry(key, new User(key, key)));

        Entry entry = this.slotLists[hash].get(index);

        //Call DynamicArray remove()
        this.slotLists[hash].remove(entry);

        //Decrement count by 1
        this.count--;

        return entry.getValue();
    }
    /**
     * Calculates a number corresponding to a slot in the HashMap instance.
     * 
     * @param target The User object to use in calculation of hash
     * 
     * @return number of a slot in HashMap instance
     */
    private int hashFunction(String key) {
        final int PRIME = 17;

        //Declare an int hash equal to -1
        int hash = PRIME;

        //Call key hashCode() method
        hash += Math.abs(key.hashCode());

        hash *= PRIME;

        //MOD hash by INITIAL_SIZE
        hash = Math.abs(hash % INITIAL_SIZE);

        return hash;
    }

    /**
     * Checks if given key is already present in given HashMap slot.
     * @param slot Given HashMap slot
     * @param key Given key
     * @return False if key is not present in slot list, true otherwise.
     */
    private boolean containsKey(String key) {
        //Declare int slot to store hashFunction(key)
        int slot = hashFunction(key);

        //Initialise for loop 
        for (int i = 0; i < slotLists[slot].size(); i++) {
            //Check Entry key is same as given key
            if (key.equalsIgnoreCase(slotLists[slot].get(i).getKey())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets all keys in HashMap instance.
     * @return A String[] with all keys
     */
    public String[] getKeys() {
        //Validation
        if (this.count == 0) {
            return null;
        }

        //Declare String[] keys
        String[] keys = new String[this.count];

        //Declare int keyCount
        int keyCount = 0;

        //Initialise for loop to go through each slot in HashMap instance
        for (int i = 0; i < INITIAL_SIZE; i++) {
            if(this.slotLists[i] == null) {
                continue;
            }

            //Nested for loop to linear search for each key
            for (int j = 0; j < slotLists[i].size(); j++) {
                //Insert key value into keys
                keys[keyCount++] = this.slotLists[i].get(j).getKey();
            }
        }
        
        return keys;
    }

    /**
     * Gets all User objects in HashMap instance
     * @return 
     */
    public User[] getValues() {
        //Validation
        if (this.count == 0) {
            return null;
        }

        //Declare User[] values
        User[] values = new User[this.count];

        //Declare int keyCount
        int valueCount = 0;

        //Initialise for loop to go through each slot in HashMap instance
        for (int i = 0; i < INITIAL_SIZE; i++) {
            if(this.slotLists[i] == null) {
                continue;
            }
            
            //Nested for loop to linear search for each key
            for (int j = 0; j < slotLists[i].size(); j++) {
                //Insert key value into keys
                values[valueCount++] = this.slotLists[i].get(j).getValue();
            }
        }
        
        return values;
    }
}