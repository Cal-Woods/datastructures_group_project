package utils;

import business.User;

/**
    * A wrapper class for HashMap key:value pairs.
    */
    public class Entry {
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
         * Updates the slot's User object with given new value.
         * @param newValue Given new value
         */
        public User updateValue(User newValue) {
            //Validation
            if(newValue == null) {
                throw new NullPointerException("Given new value is null! Please give a non-null User object as value");
            }

            User oldValue = this.value;

            this.value = newValue;

            return oldValue;
        }

        /**
         * Checks if two Entry keys are equal
         * @param o The object to compare, must be Entry
         */
        @Override
        public boolean equals(Object o) {
            //Validation
            if(o == null) {
                throw new NullPointerException("Given Object was null!");
            }
            if(!(o instanceof Entry)) {
                throw new IllegalArgumentException("Given Object was NOT an Entry object, the given object must be Entry");
            }

            //Store an Entry from given Object
            Entry entry = ((Entry)o);

            //Check if two Entry objects are identical
            if(entry.key.equals(this.key)) {
                return true;
            }

            return false;
        }

        /**
         * Converts Entry object's key into a String hashCode in accordance with equals().
         */
        @Override
        public int hashCode() {
            final int PRIME = 17;

            int hash = PRIME;

            hash += this.key.hashCode();

            hash *= PRIME;

            return hash;
        }


        @Override
        public String toString() {
            return "Key: "+this.key+"\nValue: "+this.value+"\n";
        }
    }
