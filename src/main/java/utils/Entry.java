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
         * Updates the slot's User password value with given new value.
         * @param newValue Given new value
         */
        public String updateValue(String newValue) {
            //Validation
            if(newValue == null) {
                throw new NullPointerException("Given new value is null! Please give a non-null User object as value");
            }

            String oldValue = this.value.getPassword();

            this.value.setPassword(newValue);

            return oldValue;
        }
    }
