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
    private LinkedList[] slotLists;

    //Constructors
    /**
    * A no-argument constructor that initialises instance to default empty HashMap.
    */
    public HashMap() {
        this.slotLists = new LinkedList[INITIAL_SIZE];
        this.count = 0;
    }

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
            slotLists[calculated] = new LinkedList();
        }

        //Add entry to slotLists at calculated
        slotLists[calculated].add("");
    }

    /**
     * Calculates a number corresponding to a slot in the HashMap instance.
     * 
     * @param target The User object to use in calculation of hash
     * 
     * @return number of a slot in HashMap instance
     */
    private int calcSlot(User target) {
        //Declare an int hash equal to -1
        int hash = -1;

        //Check User or Agent
        if(target instanceof Agent) {
            //Set hash to (target.hashCode mod INITIAL_SIZE)
            hash = ((Agent)target).hashCode() % INITIAL_SIZE;
        }
        
        else if(target instanceof User) {
            //Set hash to (target.hashCode mod INITIAL_SIZE)
            hash = target.hashCode() % INITIAL_SIZE;
        }

        return hash;
    }

    /**
    * A wrapper class for HashMap key:value pairs.
    */
    private static class Entry {
        //Attributes
        private final String key;
        private User value;

        //Constructor
        public Entry(String key, User value) {
            if(key == null) {
                throw new NullPointerException("Given key was null which is NOT allowed!");
            }
            if(value == null) {
                throw new NullPointerException("Given User object was null which is NOT allowed!");
            }

            this.key = key;
            this.value = value;
        }

        /**
         * Gives key for instance value in HashMap instance.
         * @return Key for value User object
         */
        public String getKey() {
            return this.key;
        }

        /**
         * Gets the value of a slot.
         * @return A User object value
         */
        public User getValue() {
            return this.value;
        }

        /**
         * Updates the slot's User password value with given new value.
         * @param newValue Given new value
         */
        public String updatePassValue(String newValue) {
            //Validation
            if(newValue == null) {
                throw new NullPointerException("Given new value is null! Please give a non-null User object as value");
            }

            String oldValue = this.value.getPassword();

            this.value.setPassword(newValue);

            return oldValue;
        }
    }
}


