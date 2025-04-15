package business;

/**
 * A basic user information entity that holds a username & password.
 * 
 * @author Cal Woods
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
        validUsername(username);
        
        this.username = username;
        return true;
    }

    /**
     * Validates a given username, can also be used to partially validate a given password value.
     * 
     * @param username Given username
     * @return true if username is valid, false otherwise
     * @throws NullPointerException If given username is null.
     * @throws IllegalArgumentException If given username is a blank String
     */
    private static void validUsername(String username) {
        if(username == null) {
            throw new NullPointerException("Given username is null. Check username argument as this is NOT a valid username.");
        }
        if(username.isBlank()) {
            throw new IllegalArgumentException("Given username was "+username+" which is blank and NOT allowed as s username");
        }
    }

    /**
     * Sets a given new password for User object instance.
     * @param newPass Given new password
     * 
     * @throws IllegalArgumentException If given new password has a length of < 8
     */
    public void setPassword(String newPass) {
        //Validation
        validUsername(newPass);

        if(newPass.length() < 8) {
            throw new IllegalArgumentException("Given new pass was too short! Must be at least eight characters in length!");
        }

        this.password = newPass;
    }
}
