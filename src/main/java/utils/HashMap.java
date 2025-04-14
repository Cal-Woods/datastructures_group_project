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
        private String key;
        private User user;

        //Constructor
        public Entry(String key, User user) {
            if(key == null) {
                throw new NullPointerException("Given key was null which is NOT allowed!");
            }
            if(user == null) {
                throw new NullPointerException("Given User object was null which is NOT allowed!");
            }

            this.key = key;
            this.user = user;
        }
    }
}


