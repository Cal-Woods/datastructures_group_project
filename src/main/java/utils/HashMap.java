package utils;

import business.Agent;
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
     * @param user Given User
     * @return Added User object or null if key is already present in HashMap.
     * 
     * @throws NullPointerException If given User is null
     */
    public User put(User user) {
        //Validation
        if(user == null) {
            throw new NullPointerException("Given User object is null which is NOT allowed!");
        }
        
        //Declare int calculated to store calculated slot
        int calculated = calcSlot(user);

        //Check if calculated slot is empty
        if(slotLists[calculated] == null) {
            //Initialise slot
            slotLists[calculated] = new DynamicArray();
        }

        //TODO: Validate key is NOT already present in slot list
        if(isKeyPresent(calculated, user.getUsername())) {
            System.err.println("Incoming key value is already present in slot list of HashMap instance.");

            return null; 
        }

        //Add Entry objects to slotLists at calculated index
        slotLists[calculated].add(user);

        this.count++;
        return user;
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
        int index = this.calcSlot(new User(key, "Does_not_matter"));

        //Check if slotLists[index] is null
        if(this.slotLists[index] == null) {
            return null;
        }

        //Initialise for loop to iterate through this.slotLists to find matching key
        for (int i = 0; i < this.slotLists[index].size(); i++) {
            //Check if given key matches Entry key
            if(key.equalsIgnoreCase(this.slotLists[index].get(i).getKey())) {
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
        int hash = -1;

        //Call key hashCode() method
        hash = key.hashCode() + PRIME;

        hash *= PRIME;

        return hash;
    }

    /**
     * Checks if given key is already present in given HashMap slot.
     * @param slot Given HashMap slot
     * @param key Given key
     * @return False if key is not present in slot list, true otherwise.
     */
    private boolean isKeyPresent(int slot, String key) {
        //Initialise for loop 
        for (int i = 0; i < slotLists[slot].size(); i++) {
            //Check Entry key is same as given key
            if (key.equalsIgnoreCase(slotLists[slot].get(i).getKey())) {
                return true;
            }
        }

        return false;
    }
}