package business;

/**
 * A basic user information entity that holds a username & password.
 */
public class User {
    private String username;
    private String password;

    //Getters
    /**
     * Gets username of User object instance.
     * 
     * @return User Username field of instance.
     * 
     * @author Cal Woods
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets password field of User instance.
     * 
     * @return User password field of instance object.
     * 
     * @author Cal Woods
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets User instance username attribute to new username.
     * @param username The given new username
     * 
     * @return True if username was set.
     * 
     * @throws IllegalArgumentException If given username is null or same is blank.
     */
    public boolean setUserName(String username) {
        //Validation
        //TODO: Put username validation into separate method
        if(username == null) {
            throw new IllegalArgumentException("Given username is null. Check username argument as this is NOT a valid username.");
        }
        if(username.isBlank()) {
            throw new IllegalArgumentException("Given username was "+username+", which is NOT valid as a username cannot contain only whitespaces.");
        }

        //TODO: Implement method for searching a list type for matching usernames
        
        
        this.username = username;
        return true;
    }
}
